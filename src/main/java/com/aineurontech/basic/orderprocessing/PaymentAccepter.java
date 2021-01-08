package com.aineurontech.basic.orderprocessing;

import java.util.concurrent.Callable;

public class PaymentAccepter implements Callable<String> {
    private Boolean inventoryPresent;


    public PaymentAccepter(Boolean inventoryPresent){
        this.inventoryPresent= inventoryPresent;

    }
    @Override
    public String call() throws Exception {
        if (inventoryPresent)
            return "payment accepted";
        else
            return "payment not accepted";

    }

}