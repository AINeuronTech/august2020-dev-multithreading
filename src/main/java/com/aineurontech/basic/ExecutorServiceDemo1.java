package com.aineurontech.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceDemo1 {

    public static void main(String[] args) {

        System.out.println("Main Thread = "+ Thread.currentThread().getName());
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 100; i++) {
           executor.submit(new MyRunnable());

        }
        executor.shutdown();


    }


    private static class MyRunnable implements Runnable {
        @Override
        public void run() {

        /*    try {
                Thread.sleep(100);
            } catch (Exception exception) {

            }*/
            System.out.println("Thread Inside Runnable=" + Thread.currentThread().getName());

        }
    }
}
