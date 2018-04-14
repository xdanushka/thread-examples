package com.rmdc.tutoraials.com.rmdc.tutoraials;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * we can synchronize stageOne() and stageTwo() methods by adding synchronized key to method signature
 * but if we do like that there will be only one lock for both methods .
 * that means if one thread lock at stage1 then another thread has to wait even if that thread want to access stage2
 *
 * because of that reason we use two locks for stage1 and stage2
 */
public class ThreadSync2 {
    Object lock1 = new Object();
    Object lock2 = new Object();

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();

    Random random = new Random();

    public void stageOne(){
        synchronized (lock1){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }
    public void stageTwo(){
        synchronized (lock2){
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }
    }

    public void doProcess(){
        for(int i=0;i<1000;i++){
            stageOne();
            stageTwo();
        }
    }

    public static void main(String[] args) {

        ThreadSync2 sync2 = new ThreadSync2();
        Thread t1 = new Thread(()->{
            sync2.doProcess();
        });
        Thread t2 = new Thread(()->{
            sync2.doProcess();
        });
        long startMil = System.currentTimeMillis();
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        long endtMil = System.currentTimeMillis();

        System.out.println("Time Consumed " + (endtMil-startMil));
        System.out.println("List 1 Size " + sync2.list1.size() + " List 2 Size " + sync2.list2.size());
    }
}
