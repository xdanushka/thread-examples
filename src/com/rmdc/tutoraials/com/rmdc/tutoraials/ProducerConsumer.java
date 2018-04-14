package com.rmdc.tutoraials.com.rmdc.tutoraials;

import com.sun.javaws.progress.PreloaderDelegate;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class ProducerConsumer {
    public static BlockingDeque<Integer> integers = new LinkedBlockingDeque<>(10);

    public static void producer(){
        while(true){
            try {
                integers.put(new Random().nextInt(10));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void consumer(){
        Random random = new Random();
        while(true){

            if(random.nextInt(10)==0){
                int val = 0;
                try {
                    val = integers.take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Value " + val+ " size " + integers.size());
            }
        }
    }

    public static void main(String[] args) {
        new Thread(ProducerConsumer::producer).start();
        new Thread(ProducerConsumer::consumer).start();
    }
}
