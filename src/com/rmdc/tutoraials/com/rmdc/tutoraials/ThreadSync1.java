package com.rmdc.tutoraials.com.rmdc.tutoraials;

public class ThreadSync1 {
    private int count=0;
    public static void main(String[] args) {
        new ThreadSync1().doWork();
    }

    public synchronized void incrementTheCount(){
        count++;
    }
    public void doWork(){
        Thread t1 = new Thread(()->{
           for(int i=0;i<10000;i++){
               incrementTheCount();
           }
        });

        Thread t2 = new Thread(()->{
           for(int i=0;i<10000;i++){
               incrementTheCount();
           }
        });

        t1.start();
        t2.start();

        //wait until threads finish
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Value count is " + count);
    }
}
