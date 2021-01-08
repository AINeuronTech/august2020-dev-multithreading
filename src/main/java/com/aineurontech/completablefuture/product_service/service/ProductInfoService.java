package com.aineurontech.completablefuture.product_service.service;


import com.aineurontech.completablefuture.product_service.domain.ProductInfo;
import com.aineurontech.completablefuture.product_service.domain.ProductOption;
import com.google.common.collect.ImmutableList;

import java.util.List;

public class ProductInfoService {

    public ProductInfo retrieveProductInfo(String productId) {
       // delay(1000);
        List<ProductOption> productOptions = ImmutableList.of(new ProductOption(1, "64GB", "Black", 699.99),
                new ProductOption(2, "128GB", "Black", 749.99));
        return ProductInfo.builder().productId(productId)
                .productOptions(productOptions)
                .build();
    }
}
