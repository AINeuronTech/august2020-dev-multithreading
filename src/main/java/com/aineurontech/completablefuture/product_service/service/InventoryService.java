package com.aineurontech.completablefuture.product_service.service;




import com.aineurontech.completablefuture.product_service.domain.Inventory;
import com.aineurontech.completablefuture.product_service.domain.ProductOption;

import java.util.concurrent.CompletableFuture;

import static com.aineurontech.completablefuture.product_service.util.CommonUtil.delay;

public class InventoryService {
    public Inventory addInventory(ProductOption productOption) {
        delay(500);
        return Inventory.builder()
                .count(2).build();

    }

    public CompletableFuture<Inventory> addInventory_CF(ProductOption productOption) {

        return CompletableFuture.supplyAsync(() -> {
            delay(500);
            return Inventory.builder()
                    .count(2).build();
        });

    }
}
