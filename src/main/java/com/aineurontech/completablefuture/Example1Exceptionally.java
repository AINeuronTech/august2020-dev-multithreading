package com.aineurontech.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class Example1Exceptionally {

    public static void main(String[] args) throws Exception {
        System.out.println("-- running CompletableFuture --");

        Supplier<Integer> divider = () -> {
            System.out.println("running task");
            return 50 / 0;
        };

        CompletableFuture<Integer> completableFuture = CompletableFuture
                .supplyAsync(divider).exceptionally(exception -> {
                    System.err.println("exception: " + exception);
                    //Swallow
                    return -1;
                });
        Thread.sleep(500);//let the stages complete
        System.out.println("-- checking exceptions --");
        System.out.println("is completedExceptionally: " + completableFuture.isCompletedExceptionally());
        System.out.println("-- getting results --");
        System.out.println(completableFuture.get());
    }
}
