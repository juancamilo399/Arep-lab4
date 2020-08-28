package co.edu.escuelaing.sparkd.httpserver;

import co.edu.escuelaing.sparkd.microspring.MicroSpring;

import java.lang.reflect.InvocationTargetException;
import java.net.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServer {

    private  MicroSpring iocServer;
    //private int port = 36000;
    private boolean running = false;

    public HttpServer() {
    }

    public HttpServer(MicroSpring iocServer) {
        this.iocServer = iocServer;
    }


    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 36000;
    }

    public void start() {
        while(true) {
            try {
                ServerSocket serverSocket = null;
                int port = getPort();

                try {
                    serverSocket = new ServerSocket(port);
                } catch (IOException e) {
                    System.err.println("Could not listen on port: " + port);
                    System.exit(1);
                }

                running = true;
                while (running) {
                    try {
                        Socket clientSocket = null;
                        try {
                            System.out.println("Listo para recibir en puerto 36000 ...");
                            clientSocket = serverSocket.accept();
                        } catch (IOException e) {
                            System.err.println("Accept failed.");
                            System.exit(1);
                        }

                        processRequest(clientSocket);

                        clientSocket.close();
                    } catch (IOException ex) {
                        Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
                serverSocket.close();
            } catch (IOException ex) {
                Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void processRequest(Socket clientSocket) throws IOException, InvocationTargetException, IllegalAccessException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(clientSocket.getInputStream()));
        String inputLine;
        Map<String, String> request = new HashMap<>();
        boolean requestLineReady = false;
        while ((inputLine = in.readLine()) != null) {
            if (!requestLineReady) {
                request.put("requestLine", inputLine);
                requestLineReady = true;
            } else {
                String[] entry = createEntry(inputLine);
                if (entry.length > 1) {
                    request.put(entry[0], entry[1]);
                }
            }
            if (!in.ready()) {
                break;
            }
        }
        Request req = new Request(request.get("requestLine"));

        System.out.println("RequestLine: " + req);
        if(!req.equals(null)) {
            createResponse(req, new PrintWriter(
                    clientSocket.getOutputStream(), true));
        }
        in.close();
    }

    private String[] createEntry(String rawEntry) {
        return rawEntry.split(":");
    }

    private void createResponse(Request req, PrintWriter out) throws InvocationTargetException, IllegalAccessException {
        String outputLine = testResponse();
        URI theuri = req.getTheuri();
        if (theuri.getPath().startsWith("/Apps")) {
            String appuri= theuri.getPath().substring(5);
            invokeApp(appuri,out);
        } else {
            getStaticResource(theuri.getPath(), out);
        }
        out.close();
    }

    private void invokeApp(String appuri, PrintWriter out) throws InvocationTargetException, IllegalAccessException {

        String header = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";
        String methodresponse = iocServer.invoke(appuri);
        out.println(header + methodresponse);
    }

    private String testResponse() {
        String outputLine = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n"
                + "<!DOCTYPE html>\n"
                + "<html>\n"
                + "<head>\n"
                + "<meta charset=\"UTF-8\">\n"
                + "<title>Title of the document</title>\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Mi propio mensaje</h1>\n"
                + "</body>\n"
                + "</html>\n";
        return outputLine;
    }

    private void getStaticResource(String path, PrintWriter out) {
        Path file = Paths.get("target/classes/public_html" + path);
        try (InputStream in = Files.newInputStream(file);
                BufferedReader reader
                = new BufferedReader(new InputStreamReader(in))) {
            String header = "HTTP/1.1 200 OK\r\n"
                + "Content-Type: text/html\r\n"
                + "\r\n";
            out.println(header);
            String line = null; 
            while ((line = reader.readLine()) != null) {
                out.println(line);
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(HttpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
