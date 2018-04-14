package com.rmdc.tutoraials.com.rmdc.tutoraials;

public class ThreadImplement implements Runnable {
    @Override
    public void run() {
        for(int i=0;i<10;i++){
            System.out.println("Hello " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new ThreadImplement());
        t1.start();
        Thread t2 = new Thread(()-> System.out.println("tq"));
        t2.start();
    }
}
