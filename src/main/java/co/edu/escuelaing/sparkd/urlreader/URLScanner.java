package co.edu.escuelaing.sparkd.urlreader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class URLScanner {

    public static void main(String[] args) {

        scanURL("https://ldbn.is.escuelaing.edu.co/AREP/Respuestas.txt");
        scanURL("https://ldbn.is.escuelaing.edu.co:6008/AREP/Respuestas.txt");
        scanURL("https://www.google.com/search?channel=trow2&client=firefox-b-d&q=covid+colombia#wptab=s:H4sIAAAAAAAAAONgVuLVT9c3NMwySk6OL8zJecTYwMgt8PLHPWGp8klrTl5jLOQS901NyUzOzEt1ySxOTSxO9clPTizJzM8T0uNic80rySypFFLhEpRCNUeDQYqfC1VISI2LA65XiotHikM_V9_AMKfYCKiYiwvO49nFxO2RmphTkhFcklhSvIhVI7W4JDHl8NrikszkxGKFlFQF5_yi_LzEssyi0mKF1DwgNyc_NykzEQAimFObzgAAAA");
        scanURL("https://ldbn.is.escuelaing.edu.co/events/ArqIS2020/index.html#TemasC");
        scanURL("https://daniel@ldbn.is.escuelaing.edu.co/events/ArqIS2020/index.html#TemasC");
    }

    public static void scanURL(String siteurl) {
        try {
            URL site = new URL(siteurl);
            System.out.println(site);
            System.out.println("------------");
            System.out.println("Protocol: " + site.getProtocol());
            System.out.println("Host: " + site.getHost());
            System.out.println("Port: " + site.getPort());
            System.out.println("Path: " + site.getPath());
            System.out.println("File: " + site.getFile());
            System.out.println("Query: " + site.getQuery());
            System.out.println("Ref.: " + site.getRef());
            System.out.println("Authority: " + site.getAuthority());
            System.out.println("------------");
            System.out.println("------------");

        } catch (MalformedURLException ex) {
            Logger.getLogger(URLReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
