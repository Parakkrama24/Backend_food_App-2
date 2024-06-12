package com.Dasanayaka.Backend.repository;

import com.Dasanayaka.Backend.model.IngreadiantItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientItemRepository extends JpaRepository<IngreadiantItem, Long > {

    //@Query("SELECT r FROM Resturent r WHERE lower(r.name) LIKE lower(concat('%', :query, '%')) OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))")

    List<IngreadiantItem> findByRestaurantId(Long id);


}
