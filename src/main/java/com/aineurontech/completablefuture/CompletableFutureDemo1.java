package com.aineurontech.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class CompletableFutureDemo1 {

    public static CompletableFuture<String> calculateAsync() throws InterruptedException {
        CompletableFuture<String> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            System.out.println("::::::"+Thread.currentThread().getName()+"::::::::");
            Thread.sleep(500);
            completableFuture.complete("Hello");
            return null;
        });

        return completableFuture;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> futureString=calculateAsync();
        System.out.println("::::::"+Thread.currentThread().getName()+"::::::::"+futureString.get());
    }
}
