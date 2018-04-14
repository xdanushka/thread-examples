package com.rmdc.tutoraials.com.rmdc.tutoraials;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class ThreadPool implements Runnable{
    private int id;
    public ThreadPool(int id){
        this.id = id;
    }

    @Override
    public void run(){
        System.out.println("Worker "+ id+ " started");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Worker "+ id+ " end");
    }

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i=0;i<5;i++) {
            executorService.submit(new ThreadPool(i));
        }

        executorService.shutdown();


        System.out.println("All Tasks Submitted");

        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All Tasks Completed");
    }

}
