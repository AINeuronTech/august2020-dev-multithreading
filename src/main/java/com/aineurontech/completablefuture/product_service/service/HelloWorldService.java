package com.aineurontech.completablefuture.product_service.service;

import java.util.concurrent.CompletableFuture;

import static com.aineurontech.completablefuture.product_service.util.CommonUtil.delay;
import static com.aineurontech.completablefuture.product_service.util.LoggerUtil.log;

public class HelloWorldService {

    public  String hello() {
        delay(1000);
        log("inside hello");
        return "hello";
    }

    public  String world() {
        delay(1000);
        log("inside world");
        return " world!";
    }

    public CompletableFuture<String> worldFuture(String input){

        return CompletableFuture.supplyAsync(()->{
            delay(1000);
            return input+" World!";
        });
    }
}
