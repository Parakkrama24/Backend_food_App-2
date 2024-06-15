package com.Dasanayaka.Backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Oder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private User customer;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "resturent_id") // Ensure the foreign key column name is specified correctly
    private Resturent restaurant; // Changed to singular form to match the `mappedBy` property

    private Long totalAmount;

    private String orderStatus;

    private LocalDateTime createdAt;

    @ManyToOne
    private Address deliveryAddress;

    @OneToMany
    private List<Oderitem> items;

    private int totalItems;

    private Long totalPrice;

    public void setCreatedAt(LocalDateTime now) {
        createdAt= now;
    }

    public void setCustomer(User user) {
        customer=user;
    }

    public void setOderStatus(String pending) {
        orderStatus= pending;
    }

    public void setDelivaryAddress(Address savedAddress) {

        deliveryAddress=savedAddress;
    }

    public void setRestaurant(Resturent _resturent) {
        restaurant=_resturent;
    }




    public void setOderitems(List<Oderitem> oderitems) {
        items=oderitems;
    }

    public void setTotalPrice(Long _totalPrice) {
        totalPrice= _totalPrice;
    }

    public String getOderStatus() {
        return  orderStatus;
    }
}
