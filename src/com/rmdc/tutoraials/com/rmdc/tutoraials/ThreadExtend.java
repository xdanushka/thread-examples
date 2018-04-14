package com.rmdc.tutoraials.com.rmdc.tutoraials;

public class ThreadExtend extends Thread {
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
        ThreadExtend extend1 = new ThreadExtend();
        extend1.start();

        ThreadExtend extend2 = new ThreadExtend();
        extend2.start();
    }
}
