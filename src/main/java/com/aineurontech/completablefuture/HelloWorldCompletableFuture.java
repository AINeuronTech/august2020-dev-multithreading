package com.aineurontech.completablefuture;

import com.aineurontech.completablefuture.product_service.service.HelloWorldService;

import java.util.concurrent.CompletableFuture;

import static com.aineurontech.completablefuture.product_service.util.CommonUtil.*;

public class HelloWorldCompletableFuture {
    public static String hellowWorld_approach1() { // over all latency is 2 second
        HelloWorldService service = new HelloWorldService();
        startTimer();
        String hello = service.hello();
        String world = service.world();
        timeTaken();
        return hello + world;
    }

    public static String hellowWorld_multiple_async_calls() {
        HelloWorldService service = new HelloWorldService();
        startTimer();
        CompletableFuture<String> helloCF = CompletableFuture.supplyAsync(() -> service.hello());
        CompletableFuture<String> worldCF = CompletableFuture.supplyAsync(() -> service.world());
        String result = helloCF.thenCombine(worldCF, (helloString, worldString) -> helloString + worldString)
                .thenApply(String::toUpperCase)
                .join(); // block the thread

        timeTaken();
        return result;
    }

    public static String hellowWorld_3_async_calls() {
        HelloWorldService service = new HelloWorldService();
        startTimer();
        CompletableFuture<String> helloCF = CompletableFuture.supplyAsync(() -> service.hello());
        CompletableFuture<String> worldCF = CompletableFuture.supplyAsync(() -> service.world());

        CompletableFuture<String> hiCF = CompletableFuture.supplyAsync(() -> {
            delay(1000);
            return "Hi CompletableFuture!";
        });

        String result = helloCF
                .thenCombine(worldCF, (helloString, worldString) -> helloString + worldString)
                .thenCombine(hiCF, (previous, current) -> previous + current)
                .thenApply(String::toUpperCase)
                .join(); // block the thread

        timeTaken();
        return result;
    }

    public static CompletableFuture<String> hellowWorld_thenCompose() { // over all latency is 2 second
        HelloWorldService service = new HelloWorldService();
        return CompletableFuture.supplyAsync(service::hello)
                .thenCompose((previous) -> service.worldFuture(previous));
    }

    public static void main(String[] args) {
       String output = hellowWorld_3_async_calls();

        System.out.println(output);

    }
}
