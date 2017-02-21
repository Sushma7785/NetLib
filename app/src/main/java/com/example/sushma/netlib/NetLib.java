package com.example.sushma.netlib;

/**
 * Created by sushma on 2/18/17.
 * Main class which initializes a request queue and starts Network dispatchers to process the requests from the queue.
 * The queue used is concurrent blocking queue
 */
public class NetLib {
    private static NetLib NetLibInstance = null;
    private CustomBlockingQueue<Request> requestQueue = null;
    private final int NO_OF_THREADS = 4;

    private NetLib() {
        requestQueue = new CustomBlockingQueue<Request>();
        for(int i=0; i<NO_OF_THREADS; i++) {
            NetworkDispatcher networkThread = new NetworkDispatcher(requestQueue);
            networkThread.start();
        }

    }

    public static NetLib getInstance() {
        if(NetLibInstance == null) {
            NetLibInstance = new NetLib();
        }
        return NetLibInstance;
    }

    public boolean addToQueue(Request request) throws InterruptedException {
        requestQueue.add(request);
        return true;
    }

}
