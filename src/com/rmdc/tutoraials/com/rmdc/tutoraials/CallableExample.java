package com.rmdc.tutoraials.com.rmdc.tutoraials;

import java.util.Random;
import java.util.concurrent.*;

public class CallableExample {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        Future<Integer> future =  service.submit(() -> {
            Random random = new Random();
            System.out.println("Thread is starting..");
            int duration = random.nextInt(4000);
            Thread.sleep(duration);
            System.out.println("Thread Finished.");
            return duration;
        });

        service.shutdown();

        try {
            System.out.println("Thread return value " + future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace(); // if callable code segment threw an error
        }
    }
}
