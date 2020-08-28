package co.edu.escuelaing.sparkd.microspring;

import co.edu.escuelaing.sparkd.microspring.RequestMapping;

public class HelloController {

    @RequestMapping(value = "/hola")
    static public String index() {
        return "Greetings from micro Spring Boot!";
    }
}

