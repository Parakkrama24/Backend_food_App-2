package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.Food;
import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.repository.FoodRepository;
import com.Dasanayaka.Backend.request.CreateFoodRequest;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImp implements  FoodService {

    @Autowired
    private FoodRepository foodRepository;



    @Override
    public Food createFood(CreateFoodRequest req, Category category, Resturent resturent) {

        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestauraent(resturent);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        return null;
    }

    @Override
    public void deleteFood(Long foodId) throws Exception {

    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId, boolean isVegitarin, boolean isNonVeg, boolean isSeasonal, String foodCategory) {
        return List.of();
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return List.of();
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        return null;
    }

    @Override
    public Food updateAvailabilityStatus(Long FoodId) throws Exception {
        return null;
    }
}
