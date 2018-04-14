package com.rmdc.tutoraials.com.rmdc.tutoraials.semaphore;

import java.util.concurrent.Semaphore;

public class Connection {
    private static Connection connection = new Connection();
    private Semaphore semaphore =new Semaphore(10);//to allow 10 connections
    private int connections=0;
    private Connection(){}

    public static Connection getInstance(){
        return connection;
    }

    public void connect(){
        try {
            semaphore.acquire();
            doConnect();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            semaphore.release();
        }
    }
    private void doConnect(){
        synchronized (this){
            connections++;
            System.out.println("Number of Connections " + connections);
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized (this){
            connections--;
        }

    }
}
