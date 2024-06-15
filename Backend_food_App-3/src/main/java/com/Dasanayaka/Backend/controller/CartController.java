package com.Dasanayaka.Backend.controller;


import com.Dasanayaka.Backend.model.Cart;
import com.Dasanayaka.Backend.model.CartItem;
import com.Dasanayaka.Backend.request.AddCartItemRequest;
import com.Dasanayaka.Backend.request.updateCartItemRequest;
import com.Dasanayaka.Backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CartController {


    @Autowired
private CartService cartService;

    @PutMapping("/cart/add")
    private ResponseEntity<CartItem> addItemToCart(@RequestBody AddCartItemRequest req, @RequestHeader("Authorization ") String jwt) throws Exception {

        CartItem cartItem= cartService.addItemToCart(req,jwt);
        return new ResponseEntity<>(cartItem, HttpStatus.OK);


    }
    @PutMapping("/cart-item/update")
    private ResponseEntity<CartItem> updateCartItemQuantity(@RequestBody updateCartItemRequest req, @RequestHeader("Authorization ") String jwt) throws Exception {

        CartItem cartItem= cartService.updateCartItemQuantity(req.getCartItemId(),req.getQuantity());
        return new ResponseEntity<>(cartItem, HttpStatus.OK);

    }

    @DeleteMapping("/cart-item{id}/update")
    private ResponseEntity<Cart> deleteCartItem(@PathVariable Long id, @RequestHeader("Authorization ") String jwt) throws Exception {

        Cart cart= cartService.removeItemFromCart(id,jwt);
        return new ResponseEntity<>(cart, HttpStatus.OK);

    }





}
