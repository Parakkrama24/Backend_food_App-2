package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.Catagory;

import java.util.List;

public interface CategoryService {


    public Catagory CreateCategory(String name ,Long userId) throws Exception;

    public List <Catagory> findCategoryByRestaurantId(Long id) throws  Exception;

    public Catagory findCategoryById(Long id) throws  Exception;
}

