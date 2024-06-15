package com.Dasanayaka.Backend.repository;

import com.Dasanayaka.Backend.model.Oder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository  extends JpaRepository<Oder,Long> {

    public List<Oder>  findByCustomerId(Long userId);
    public  List<Oder> findByRestaurantId(Long RestaurantId);
}
