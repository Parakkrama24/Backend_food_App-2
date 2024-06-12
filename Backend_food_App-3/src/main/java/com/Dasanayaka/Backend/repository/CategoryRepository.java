package com.Dasanayaka.Backend.repository;

import com.Dasanayaka.Backend.model.Catagory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Catagory, Long> {

    @Query("SELECT c FROM Catagory c JOIN c.resturents r WHERE r.id = :restaurantId")
    List<Catagory> findByRestaurantId(@Param("restaurantId") Long restaurantId);
}
