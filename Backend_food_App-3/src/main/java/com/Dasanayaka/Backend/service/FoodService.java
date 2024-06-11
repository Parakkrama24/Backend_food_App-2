package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.Catagory;
import com.Dasanayaka.Backend.model.ECatagory;
import com.Dasanayaka.Backend.model.Food;
import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.request.CreateFoodRequest;


import java.util.List;

public interface FoodService {

    public Food createFood(CreateFoodRequest req , Catagory category, Resturent resturent);
    void deleteFood (Long foodId) throws  Exception;

    public List<Food> getRestaurantsFood(Long restaurantId ,boolean isVegitarin, boolean isNonVeg , boolean isSeasonal, String foodCategory);

    public List<Food> searchFood (String keyword);
    public Food findFoodById(Long foodId) throws  Exception;
    public Food updateAvailabilityStatus (Long FoodId)throws  Exception;



}
