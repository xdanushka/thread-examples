package com.rmdc.tutoraials.com.rmdc.tutoraials;

import java.util.Scanner;

public class WaitAndNotify {
    public void produce(){
        synchronized (this){
            System.out.println("Producer Running...");
            try {
                wait(); //release the lock
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Producer Resumed...");
        }
    }
    public void consume(){
        synchronized (this){
            Scanner scanner = new Scanner(System.in);
            try {
                Thread.sleep(2000);
                System.out.println("Consumer Running press return to notify");
                scanner.nextLine();
                notify();
                Thread.sleep(5000);
                System.out.println("Consumer Notified");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    public static void main(String[] args) {
        WaitAndNotify waitAndNotify  =new WaitAndNotify();
        Thread t1 = new Thread(waitAndNotify::produce);
        Thread t2 = new Thread(waitAndNotify::consume);
        t1.start();
        t2.start();
    }
}

