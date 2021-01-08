package com.aineurontech.basic;

import java.time.Instant;
import java.util.concurrent.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class ExecutorServiceDemo2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Instant start = Instant.now();
        System.out.println("Main Start time " + start);
        ExecutorService executor = Executors.newFixedThreadPool(4);
        ThreadNameFetcher threadNameFetcher = new ThreadNameFetcher();


        System.out.println("Returned Value ::: "+executor.submit(threadNameFetcher));

        Future future= executor.submit(threadNameFetcher);

        System.out.println(" future  =="+future.get());
        Instant end = Instant.now();
        System.out.println("Time taken " + end);
        sleep(300);
        executor.shutdown();
    }

    private static class ThreadNameFetcher implements Callable {
        @Override
        public String call() throws Exception {
            try {
                sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" Job completed successfully : Name of the thread is" + currentThread().getName());
            Instant end = Instant.now();
            System.out.println("Fetcher End time " + end);
            return currentThread().getName();
        }
    }
}
