package com.rmdc.tutoraials.com.rmdc.tutoraials.deadlock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {

    private Account acc1 = new Account();
    private Account acc2 = new Account();

    private Lock lock1 = new ReentrantLock(); //lock for account 1
    private Lock lock2 = new ReentrantLock(); //lock for account 2

    private void accuireLocks(Lock firstLock, Lock secondLock){
        while(true){
            boolean gotFirstLock = false;
            boolean gotSecondLock = false;
            try{
                gotFirstLock = firstLock.tryLock();
                gotSecondLock = secondLock.tryLock();
            }finally {
                if(gotFirstLock && gotSecondLock){
                    return;
                }
                if(gotFirstLock){
                    firstLock.unlock();
                }
                if(gotSecondLock){
                    secondLock.unlock();
                }
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //money transferring to account 2 from account 1 therefore we have to first lock the account 1 and then account 2
    public void firstThread(){
        Random random = new Random();
        for(int i=0;i<=10000;i++){

//            lock1.lock();
//            lock2.lock();
            accuireLocks(lock1,lock2);
            try {
                Account.transfer(acc1, acc2, random.nextInt(100));
            }finally {
                lock1.unlock();
                lock2.unlock();
            }
        }
    }

    //reverse of first thread so we have to lock the account 2 initially
    public void secondThread(){
        Random random = new Random();
        for(int i=0;i<=10000;i++){
//            lock2.lock();
//            lock1.lock();
            accuireLocks(lock2,lock1);
            try {
                Account.transfer(acc2, acc1, random.nextInt(100));
            }finally {
                lock2.unlock();
                lock1.unlock();
            }
        }
    }

    public void finish(){
        System.out.println("Account 1 balance " + acc1.getBalance());
        System.out.println("Account 2 balance " + acc2.getBalance());
        System.out.println("Total balance " + (acc1.getBalance() + acc2.getBalance()));
    }

    public static void main(String[] args) {
        final Runner runner = new Runner();
        Thread t1 = new Thread(runner::firstThread);
        Thread t2 = new Thread(runner::secondThread);

        t1.start();
        t2.start();


        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        runner.finish();
    }
}
