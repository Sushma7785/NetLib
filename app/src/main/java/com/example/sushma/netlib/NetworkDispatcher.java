package com.example.sushma.netlib;

import android.os.Process;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;

/**
 * Created by sushma on 2/18/17.
 * Thread class which takes requests from the concurrent blocking queue and process them accordingly
 */
public class NetworkDispatcher extends Thread {
    private final CustomBlockingQueue<Request> requestQueue;

    public NetworkDispatcher(CustomBlockingQueue<Request> requestQueue) {
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        Process.setThreadPriority(Process.THREAD_PRIORITY_BACKGROUND);
        while(true) {
            try {
                Request currentRequest = requestQueue.take();
                Request.Method method = currentRequest.method;
                sleep(5000);
                switch(method) {
                    case GET:
                        HttpURLConnection connection = (HttpURLConnection) currentRequest.url.openConnection();
                        int code = connection.getResponseCode();
                        Log.i("TEST", currentRequest.request_name + ", " + currentThread().getName() + ", " + "Response code " + code);
                        break;
                    default:
                        System.out.println("Only supports GET requests");

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
