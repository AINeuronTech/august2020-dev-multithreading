package com.aineurontech.completablefuture.product_service.util;

public class LoggerUtil {

    public static void log(String message){
        System.out.println("["+Thread.currentThread().getName() +"] -"+message);
    }
}
