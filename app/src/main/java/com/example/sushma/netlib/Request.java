package com.example.sushma.netlib;

import java.net.URL;

/**
 * Created by sushma on 2/18/17.
 * Request class which has fields like the request method and URL
 */
public class Request {

    public String request_name;
    public Method method;
    public URL url;

    public enum Method {
        GET,
        POST,
        PUT,
        DELETE
    }

    public Request(String request_name, Method method, URL url) {
        this.request_name = request_name;
        this.method = method;
        this.url = url;
    }
}
