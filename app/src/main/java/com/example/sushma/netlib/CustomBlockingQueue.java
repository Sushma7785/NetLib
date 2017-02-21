package com.example.sushma.netlib;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sushma on 2/20/17.
 */
public class CustomBlockingQueue<T> {
    private List<T> queue = new LinkedList<T>();
    private int  limit = 100;

    public CustomBlockingQueue(){

    }


   public synchronized void add(T request) throws InterruptedException{
       /* if the queue is full, the threads trying to add requests to the queue should wait.
            This is only needed if there is a limit to the queue size. If there is no limit, this condition need not be checked.
        */

      while(this.queue.size() == this.limit) {
           wait();
       }

       // notifyAll is only called when queue is empty because, there will not be any threads waiting to dequeue if the queue size is > 0
       if(this.queue.size() == 0) {
           notifyAll();
       }

       //if the queue is not full, just add the request to the queue
       this.queue.add(request);

   }

   public synchronized T take() throws InterruptedException {
       //if the queue is empty, the threads trying to take requests from the queue should wait
       while(this.queue.size() == 0) {
           wait();
       }

       //notifyAll is only called when the queue if full because, there will not be any threads waiting to add to the queue unless the queue is full
       if(this.queue.size() == this.limit) {
           notifyAll();
       }

       //if the queue is not empty, just take the request from the queue
       return this.queue.remove(0);
   }
}
