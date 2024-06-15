package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.Cart;
import com.Dasanayaka.Backend.model.CartItem;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.request.AddCartItemRequest;

public interface CartService {

    public CartItem addItemToCart(AddCartItemRequest req , String jwt) throws Exception;

    public CartItem updateCartItemQuantity(Long cartItemId,int quantity) throws  Exception;

    public Cart removeItemFromCart(Long cartItem, String jwt) throws Exception;

    public Long calculatedCartTotals(Cart cart) throws Exception;

    public Cart findCartById(Long id) throws  Exception;

    public Cart findCartByUserId(Long userId) throws Exception;

    public  Cart  clearCart(Long userId) throws  Exception;
 }
