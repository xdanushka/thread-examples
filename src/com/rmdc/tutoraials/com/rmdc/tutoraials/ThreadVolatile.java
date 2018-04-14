package com.rmdc.tutoraials.com.rmdc.tutoraials;

import java.util.Scanner;

public class ThreadVolatile extends Thread {
//volatile keyword prevents threads from caching variables.
    private volatile boolean running = true;

    @Override
    public void run() {
        while(running){
            System.out.println("Thread is running");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutDown(){
        running = false;
    }

    public static void main(String[] args) {
        ThreadVolatile thread = new ThreadVolatile();
        thread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            thread.shutDown();
        }

    }
}
