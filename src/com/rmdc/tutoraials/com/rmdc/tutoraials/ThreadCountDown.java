package com.rmdc.tutoraials.com.rmdc.tutoraials;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadCountDown implements Runnable{
    private CountDownLatch latch;
    public ThreadCountDown(CountDownLatch latch){
        this.latch = latch;
    }


    @Override
    public void run() {
        System.out.println("Thread Started");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        latch.countDown();
    }


    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService executor = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++){
            executor.submit(new ThreadCountDown(latch));
        }
        executor.shutdown();

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed");
    }
}
