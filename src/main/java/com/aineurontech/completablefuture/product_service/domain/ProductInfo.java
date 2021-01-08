package com.aineurontech.completablefuture.product_service.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data // getting setter, getter,toString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductInfo {
    private String productId;
    private List<ProductOption> productOptions;
}
