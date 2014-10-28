package com.tengen;

import static spark.Spark.*;
import spark.*;
 
public class HelloWorldSparkStyle {
    public static void main(String[] args) {
        get(new Route("/hello") {
            @Override
            public Object handle(Request request, Response response) {
                return "Hello Spark MVC Framework!";
            }
        });
    }
}