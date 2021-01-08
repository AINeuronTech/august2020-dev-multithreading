package com.aineurontech.basic.orderprocessing;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        Instant start = Instant.now();
        System.out.println("Start time:" + start);

        Supplier<Integer> orderFetcher = () -> {
            sleep(200);
            int random = ThreadLocalRandom.current().nextInt(6);
           // System.out.println(random);
            return  random;
        };

        Function<Integer, Boolean > inventoryChecker = orderNumber -> {
            sleep(200);
            if (orderNumber % 2 == 0)
                return Boolean.TRUE;
            else
                return Boolean.FALSE;
        };

        Consumer<Boolean> paymentAcceptor1= new Consumer<Boolean>() {
            @Override
            public void accept(Boolean isinventoryPresent) {
                if (isinventoryPresent) {
                    System.out.println("payment accepted");
                } else {
                    System.out.println("payment not accepted");
                }

            }
        };

        Consumer<Boolean> paymentAcceptor2 = isinventoryPresent -> {
            if (isinventoryPresent) {
                System.out.println("payment accepted");
            } else {
                System.out.println("payment not accepted");
            }

        };
        //Replace paymentAcceptor1 with paymentAcceptor2, both would give identical output

        for(int i =0; i<=9; i++) {
            CompletableFuture.supplyAsync(orderFetcher, executor)
                    .thenApply(inventoryChecker)
                    .thenAccept(paymentAcceptor1);
        }


        //System.out.println(cf1.join());
        sleep(200);
        Instant end = Instant.now();
        long timeElapsed = Duration.between(start, end).toMillis();
        System.out.println("Total time " + timeElapsed);
        executor.shutdown();

    }
    private static void sleep(int timeout) {
        try {
            Thread.sleep(timeout);
        } catch (InterruptedException ignored) {
        }
    }
}
