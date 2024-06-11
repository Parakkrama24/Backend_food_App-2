package com.Dasanayaka.Backend.repository;

import com.Dasanayaka.Backend.model.Resturent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RestorentRepository extends JpaRepository<Resturent, Long> {

    @Query("SELECT r FROM Resturent r WHERE lower(r.name) LIKE lower(concat('%', :query, '%')) OR lower(r.cuisineType) LIKE lower(concat('%', :query, '%'))")
    List<Resturent> findBySearchQuary(String query);

    Resturent findByOwnerId(Long userId);
}
