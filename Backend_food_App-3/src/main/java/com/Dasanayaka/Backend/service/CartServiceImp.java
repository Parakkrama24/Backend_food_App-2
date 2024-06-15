package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.Cart;
import com.Dasanayaka.Backend.model.CartItem;
import com.Dasanayaka.Backend.model.Food;
import com.Dasanayaka.Backend.model.User;
import com.Dasanayaka.Backend.repository.CartItemRepository;
import com.Dasanayaka.Backend.repository.CartRepository;
import com.Dasanayaka.Backend.request.AddCartItemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private Userservice userservice;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private FoodService foodService;

    @Override
    public CartItem addItemToCart(AddCartItemRequest req, String jwt) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
        Food food = foodService.findFoodById(req.getFoodId());
        Cart cart = cartRepository.findByCustomerId(user.getId());

        for (CartItem cartItem : cart.getItemsList()) {
            if (cartItem.getFood().equals(food)) {
                int newQuantity = cartItem.getQuantity() + req.getQuantity();
                return updateCartItemQuantity(cartItem.getId(), newQuantity);
            }
        }

        CartItem newCartItem = new CartItem();
        newCartItem.setFood(food);
        newCartItem.setCart(cart);
        newCartItem.setQuantity(req.getQuantity());
        newCartItem.setIngredients(req.getIngredients());
        newCartItem.setTotalPrice(req.getQuantity() * food.getPrice());

        cart.getItemsList().add(newCartItem); // Add the new cart item to the cart's items list
        cartItemRepository.save(newCartItem);
        cartRepository.save(cart);

        return newCartItem;
    }

    @Override
    public CartItem updateCartItemQuantity(Long cartItemId, int quantity) throws Exception {
        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()) {
            throw new Exception("Cart item not found");
        }
        CartItem item = cartItemOptional.get();
        item.setQuantity(quantity);
        item.setTotalPrice(item.getPrice()); // Use the getPrice method to set the total price
        cartItemRepository.save(item);
        return item;
    }

    @Override
    public Cart removeItemFromCart(Long cartItemId, String jwt) throws Exception {
        User user = userservice.findUsserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        Optional<CartItem> cartItemOptional = cartItemRepository.findById(cartItemId);
        if (cartItemOptional.isEmpty()) {
            throw new Exception("Cart item not found");
        }

        CartItem item = cartItemOptional.get();
        cart.getItemsList().remove(item); // Remove the item from the cart's list of items
        cartItemRepository.delete(item); // Delete the item from the repository
        cartRepository.save(cart); // Save the updated cart

        return cart;
    }


    @Override
    public Long calculatedCartTotals(Cart cart) throws Exception {

        Long totol=0L;
        for(CartItem cartItem: cart.getItems()){
            totol+=cartItem.getFood().getPrice()*cartItem.getQuantity();
        }
        return totol;
    }

    @Override
    public Cart findCartById(Long id) throws Exception {
     Optional<Cart> optionalCart =cartRepository.findById(id);
     if(optionalCart.isEmpty()){
         throw new Exception("cart not found");
     }
        return optionalCart.get();
    }

    @Override
    public Cart findCartByUserId(String  jwt) throws Exception {
        User user=userservice.findUsserByJwtToken(jwt);

        return cartRepository.findByCustomerId(user.getId());
    }

    @Override
    public Cart clearCart(String jwt) throws Exception {
        User user=userservice.findUsserByJwtToken(jwt);
        Cart cart = cartRepository.findByCustomerId(user.getId());

        if (cart == null) {
            throw new Exception("Cart not found");
        }

        cart.getItemsList().clear();
        cartRepository.save(cart);

        return cart;
    }

}
