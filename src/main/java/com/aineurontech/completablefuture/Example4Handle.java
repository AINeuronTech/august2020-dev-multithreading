package com.aineurontech.completablefuture;

import java.util.concurrent.CompletableFuture;

public class Example4Handle {
    public static void main(String[] args) throws Exception {
      runTasks(2);
      // runTasks(0);
    }
    private static void runTasks(int i) {
        System.out.printf("-- input: %s --%n", i);
        CompletableFuture
                .supplyAsync(() -> {
                    return 50 / i;
                }).handle((input, exception) -> {
            if (exception != null) {
                System.out.println("exception block");
                System.err.println(exception);
                return -1;
            } else {
                System.out.println("normal execution  block");
                return input;
            }})
                .thenApply(input -> input * 3)
                .thenAccept(System.out::println);
    }
}
