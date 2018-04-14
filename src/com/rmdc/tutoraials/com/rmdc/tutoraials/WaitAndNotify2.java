package com.rmdc.tutoraials.com.rmdc.tutoraials;

import java.util.LinkedList;
import java.util.Scanner;

public class WaitAndNotify2 {
    private LinkedList<Integer> integers = new LinkedList<>();
    private int MAX = 10;
    Object lock = new Object();
    public void produce(){
        int val = 0;
        while(true) {
            synchronized (lock) {

                while (integers.size() == MAX) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                integers.push(val++);
                lock.notify();
            }
        }
    }
    public void consume(){
        while(true) {
            synchronized (lock) {
                while (integers.size() == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print("List size is " + integers.size());
                int value = integers.removeFirst();
                System.out.println(" Consumed value is " + value);
                lock.notify();
            }
        }
    }

    public static void main(String[] args) {
        WaitAndNotify2 waitAndNotify2  =new WaitAndNotify2();
        Thread t1 = new Thread(waitAndNotify2::produce);
        Thread t2 = new Thread(waitAndNotify2::consume);
        t1.start();
        t2.start();
    }
}

