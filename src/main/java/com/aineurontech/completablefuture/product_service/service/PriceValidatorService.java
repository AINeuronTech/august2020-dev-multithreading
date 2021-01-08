package com.aineurontech.completablefuture.product_service.service;


import com.aineurontech.completablefuture.product_service.checkout.CartItem;

import static com.aineurontech.completablefuture.product_service.util.CommonUtil.delay;

public class PriceValidatorService {

    public boolean isCartItemInvalid(CartItem cartItem){
        int cartId = cartItem.getItemId();
        delay(500);
        if (cartId == 7 || cartId == 9 || cartId == 11) {
            return true;
        }
        return false;
    }
}
