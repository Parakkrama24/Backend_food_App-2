package com.Dasanayaka.Backend.repository;

import com.Dasanayaka.Backend.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CartRepository extends JpaRepository<Cart, Long> {

//    @Query("SELECT r FROM Cart r WHERE r.customerId = :customerId")
//    Cart findByCustomerId(@Param("customerId") Long customerId);

}
