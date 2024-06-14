package com.Dasanayaka.Backend.repository;

import com.Dasanayaka.Backend.model.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategory,Long> {

    //@Query("SELECT r FROM ingredient_categories r WHERE lower(r.name) LIKE lower(concat('%',query, '%')) OR lower(r.cuisineType) LIKE lower(concat('%', query, '%'))")
    List<IngredientCategory> findByRestaurantId(Long id);

}
