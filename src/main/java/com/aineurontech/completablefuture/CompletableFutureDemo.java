package com.aineurontech.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDemo {

    public static CompletableFuture<String> supplyAssync_And_thenApplyDemo(){
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("Thread Name ="+Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenApply(s -> {
            System.out.println(" Input = "+ s);
            System.out.println("Thread Name ="+Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s +" World";
        });

        return completableFuture;
    }

    public static CompletableFuture<String> supplyAssync_And_thenApplyAsyncDemo(){
        CompletableFuture completableFuture = CompletableFuture.supplyAsync(()->{
            System.out.println("Thread Name ="+Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Hello";
        }).thenApplyAsync(s -> {
            System.out.println(" Input = "+ s);
            System.out.println("Thread Name ="+Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return s +" World";
        });

        return completableFuture;
    }

    public static CompletableFuture<String> thenCombineAsync() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture1 = CompletableFuture.supplyAsync(() -> "Future");
        CompletableFuture<String> completableFuture2 = CompletableFuture.supplyAsync(() -> " is awesome!");

        CompletableFuture<String> combinedCompletableFuture = completableFuture1.thenCombineAsync(completableFuture2, (s1, s2) -> s1.concat(s2));

        return combinedCompletableFuture;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //CompletableFuture<String> cFuture= supplyAssync_And_thenApplyAsyncDemo();//supplyAssync_And_thenApplyDemo();

        CompletableFuture<String> cFuture=thenCombineAsync();
        String output= cFuture.join();
        System.out.println(output);
    }
}
