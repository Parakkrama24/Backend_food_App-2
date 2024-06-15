package com.Dasanayaka.Backend.repository;

import com.Dasanayaka.Backend.model.Cart;
import com.Dasanayaka.Backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartItemRepository extends JpaRepository<CartItem ,Long> {
    @Query("SELECT r FROM CartItem r WHERE r.id = :id")
    Cart findByCustomerId(@Param("id") Long customerId);
}
