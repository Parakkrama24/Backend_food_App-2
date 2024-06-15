package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.*;
import com.Dasanayaka.Backend.repository.AddressRepository;
import com.Dasanayaka.Backend.repository.OrderItemRepository;
import com.Dasanayaka.Backend.repository.OrderRepository;
import com.Dasanayaka.Backend.repository.UserRepository;
import com.Dasanayaka.Backend.request.OderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class OderserviceImp implements  OderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestorentService restorentService;

    @Autowired
    private CartService cartService;

    @Override
    public Oder createOder(OderRequest order, User user) throws Exception {
        Address shippingAddress = order.getDilevaryAddress();
        Address savedAddress = addressRepository.save(shippingAddress);
        if (!user.getAddress().contains(savedAddress)) {
            user.getAddress().add(savedAddress);
            userRepository.save(user);
        }
        Resturent resturent = restorentService.findRestorentById(order.getResaurantId());
        Oder createdOrder = new Oder();
        createdOrder.setCustomer(user);
        createdOrder.setCreatedAt(LocalDateTime.now());
        createdOrder.setOderStatus("PENDING");
        createdOrder.setDelivaryAddress(savedAddress);
        createdOrder.setRestaurant(resturent);

        Cart cart = cartService.findCartByUserId(user.getId());
        List<Oderitem> oderitems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {
            Oderitem oderitem = new Oderitem();
            oderitem.setFood(cartItem.getFood());
            oderitem.setIngrediants(cartItem.getIngredients());
            oderitem.setQuantity(cartItem.getQuantity());
            oderitem.setTotolPrice(cartItem.getPrice());

            Oderitem savedOderItem = orderItemRepository.save(oderitem);
            oderitems.add(savedOderItem);
        }

        Long totalPrice = cartService.calculatedCartTotals(cart);

        createdOrder.setOderitems(oderitems); // Set the order items to the order
        createdOrder.setTotalPrice(totalPrice);
      Oder saveOder=  orderRepository.save(createdOrder); // Save the order

        resturent.getOrders().add(saveOder);

        return createdOrder; // Return the created order
    }

    @Override
    public Oder updateOder(Long oderId, String oderStatus) throws Exception {

        Oder oder= findOderById(oderId);
        if(oderStatus.equals("OUT_FOR_DELIVERY")
                || oderStatus.equals("DELIVERED")
                || oderStatus.equals("COMPLETED")
                ||  oderStatus.equals("PENDING")

        ){
            oder.setOderStatus(oderStatus);
            return  orderRepository.save(oder);
        }
        throw   new Exception("Please select a valid oder status");
    }

    @Override
    public void cancelOder(Long oderId) throws Exception {

        Oder oder  =findOderById(oderId);
        orderRepository.deleteById(oderId);

    }

    @Override
    public List<Oder> getUsersOder(Long userId) throws Exception {
        return orderRepository.findByCustomerId(userId);

    }

    @Override
    public List<Oder> getRestaurantOrders(Long restaurantId, String oderStatus) throws Exception {
        List<Oder> oders= orderRepository.findByRestaurantId(restaurantId);
        if(oderStatus!= null){
            oders=oders.stream().filter(oder -> oder.getOderStatus().equals(oderStatus)).collect(Collectors.toList());
        }
        return  oders;
    }

    @Override
    public Oder findOderById(Long oderId) throws Exception {
        Optional <Oder> optionalOder = orderRepository.findById(oderId);
        if(optionalOder.isEmpty()){
            throw  new Exception("Oder not  found");
        }
        return optionalOder.get();
    }
}
