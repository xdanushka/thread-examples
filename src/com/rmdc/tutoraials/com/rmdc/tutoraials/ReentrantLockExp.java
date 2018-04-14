package com.rmdc.tutoraials.com.rmdc.tutoraials;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExp {
    private ReentrantLock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    int count;

    public void increment(){
        for (int i = 0 ; i < 1000 ; i++){
            count++;
        }
    }

    public void thread1(){
        lock.lock();

        try {
            System.out.println("Await...");
            condition.await();
            increment();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }


    }
    public void thread2(){
        lock.lock();
        try {
            Thread.sleep(1000);
            condition.signal();
            System.out.println("Signal...");
            increment();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockExp exp = new ReentrantLockExp();
        Thread t1 = new Thread(exp::thread1);
        Thread t2 = new Thread(exp::thread2);

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Count: " + exp.count);
    }
}
