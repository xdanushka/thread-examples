package com.rmdc.tutoraials.com.rmdc.tutoraials.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * semaphores use to control access to some resources
 */
public class SemaphoreExample {
    public static void main(String[] args) {
        Connection connection = Connection.getInstance();
        ExecutorService service = Executors.newCachedThreadPool();
        for(int i=0; i<20;i++){
            service.submit(connection::connect);
        }
        service.shutdown();
        try {
            service.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
