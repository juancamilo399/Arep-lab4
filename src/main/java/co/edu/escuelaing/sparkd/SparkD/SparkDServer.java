/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.escuelaing.sparkd.SparkD;

import co.edu.escuelaing.sparkd.httpserver.HttpServer;
import co.edu.escuelaing.sparkd.microspring.MicroSpring;

/**
 *
 * @author dnielben
 */
public class SparkDServer {
    
    public static void main(String[] args){
        String[] args1=new String[1];
        args1[0]="co.edu.escuelaing.sparkd.microspring.HelloController";
        try {
        MicroSpring microSpring= new MicroSpring();
        microSpring.starts(args1);
        HttpServer server = new HttpServer(microSpring);
        server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
