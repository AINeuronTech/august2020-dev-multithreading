package com.aineurontech.completablefuture.product_service;


import com.aineurontech.completablefuture.product_service.domain.Product;
import com.aineurontech.completablefuture.product_service.domain.ProductInfo;
import com.aineurontech.completablefuture.product_service.domain.Review;
import com.aineurontech.completablefuture.product_service.service.ProductInfoService;
import com.aineurontech.completablefuture.product_service.service.ReviewService;

import java.util.concurrent.CompletableFuture;

import static com.aineurontech.completablefuture.product_service.util.CommonUtil.stopWatch;
import static com.aineurontech.completablefuture.product_service.util.LoggerUtil.log;


public class ProductServiceUsingCompletableFuture {
    private ProductInfoService productInfoService;
    private ReviewService reviewService;

    public ProductServiceUsingCompletableFuture(ProductInfoService productInfoService, ReviewService reviewService) {
        this.productInfoService = productInfoService;
        this.reviewService = reviewService;
    }

    public Product retrieveProductDetails(String productId) {
        stopWatch.start();

        CompletableFuture<ProductInfo> productInfoServiceCF = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));

        CompletableFuture<Review> reviewCF = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));

        Product product = productInfoServiceCF
                .thenCombine(reviewCF, (prodInfo, review) -> {
                    return new Product(productId, prodInfo, review);
                }).join(); // block the thread
        stopWatch.stop();
        log("Total Time Taken : " + stopWatch.getTime());
        return product;
    }

    public CompletableFuture<Product> retrieveProductDetails_approch2(String productId) { // Server based approach
        CompletableFuture<ProductInfo> productInfoServiceCF = CompletableFuture.supplyAsync(() -> productInfoService.retrieveProductInfo(productId));

        CompletableFuture<Review> reviewCF = CompletableFuture.supplyAsync(() -> reviewService.retrieveReviews(productId));
        return productInfoServiceCF
                .thenCombine(reviewCF, (prodInfo, review) -> {
                    return new Product(productId, prodInfo, review);
                });
    }

    public static void main(String[] args) {

        ProductInfoService productInfoService = new ProductInfoService();
        ReviewService reviewService = new ReviewService();
        ProductServiceUsingCompletableFuture productService = new ProductServiceUsingCompletableFuture(productInfoService, reviewService);
        String productId = "ABC123";
        Product product = productService.retrieveProductDetails(productId);
        log("Product is " + product);

    }
}
