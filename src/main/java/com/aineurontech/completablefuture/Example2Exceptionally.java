package com.aineurontech.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.function.Supplier;

public class Example2Exceptionally {
    public static void main(String[] args) {
        runTasks(0);
        //runTasks(2);
    }
    private static void runTasks(int i) {
        System.out.printf("-- input: %s --%n", i);
        Supplier<Integer> divider = () -> {
            System.out.println("running task");
            return 50 / i;
        };

        Function<Throwable, Integer> exceptionchecker = exception -> {
            System.out.println("in exceptionally");
            System.err.println(exception);
            return -1;
        };

        CompletableFuture
                .supplyAsync(divider).exceptionally(exceptionchecker)
                .thenApply(input -> input * 3)
                .thenAccept(System.out::println);
    }
}
