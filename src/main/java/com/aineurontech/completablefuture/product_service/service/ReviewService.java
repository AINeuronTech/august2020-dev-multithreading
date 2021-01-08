package com.aineurontech.completablefuture.product_service.service;

import com.aineurontech.completablefuture.product_service.domain.Review;

import static com.aineurontech.completablefuture.product_service.util.CommonUtil.delay;

public class ReviewService {

    public Review retrieveReviews(String productId) {
        delay(1000);
        return new Review(200, 4.5);
    }
}
