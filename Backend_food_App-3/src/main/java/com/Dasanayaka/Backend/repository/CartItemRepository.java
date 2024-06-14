package com.Dasanayaka.Backend.repository;

import com.Dasanayaka.Backend.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem ,Long> {
}
