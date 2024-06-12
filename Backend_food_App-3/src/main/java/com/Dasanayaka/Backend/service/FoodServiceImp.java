package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.Catagory;
import com.Dasanayaka.Backend.model.ECatagory;
import com.Dasanayaka.Backend.model.Food;
import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.repository.FoodRepository;
import com.Dasanayaka.Backend.request.CreateFoodRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodServiceImp implements  FoodService {

    @Autowired
    private FoodRepository foodRepository;



    @Override
    public Food createFood(CreateFoodRequest req, Catagory category, Resturent resturent) {

        Food food = new Food();
        food.setFoodCategory(category);
        food.setRestauraent(resturent);
        food.setDescription(req.getDescription());
        food.setImages(req.getImages());
        food.setName(req.getName());
        food.setPrice(req.getPrice());
        food.setIngredientItem(req.getIngrediants());
        food.setSeasonal(req.isSeasional());
        food.setVegetarian(req.isVegitarin());
        Food savedFood=  foodRepository.save(food);
        resturent.getFood().add(savedFood);
        return savedFood;

    }

    @Override
    public void deleteFood(Long foodId) throws Exception {
        Food food= findFoodById(foodId);
        food.setRestaurant(null);
        foodRepository.save(food);


    }

    @Override
    public List<Food> getRestaurantsFood(Long restaurantId,
                                         boolean isVegitarin,
                                         boolean isNonVeg,
                                         boolean isSeasonal,
                                         String foodCategory) {

        List<Food> food = foodRepository.findByRestaurantId(restaurantId);

        if(isVegitarin){
            food= filterByVegitarian(food,isVegitarin);
        }
        if(isNonVeg){
            food=filterByNonVeg(food, isNonVeg);
        }
        if(isSeasonal){
            food= filterBySeasonal(food, isSeasonal);
        }
        if(foodCategory!= null && !foodCategory.equals("")){
            food=filterByCategory(food,foodCategory);
        }
        return food;
    }

    private List<Food> filterByCategory(List<Food> foods, String foodCategory) {
        return foods.stream().filter(food -> {
            if(food.getFoodCategory()!= null){
                return food.getFoodCategory().getName().equals(foodCategory);
            }
            return false;
        }).collect(Collectors.toList());

    }

    private List<Food> filterBySeasonal(List<Food> foods, boolean isSeasonal) {
        return foods.stream().filter(food -> food.isSeasonal()==isSeasonal ).collect(Collectors.toList());
    }

    private List<Food> filterByNonVeg(List<Food> foods, boolean isNonVeg) {
        return foods.stream().filter(food -> food.isVegitarin()==false ).collect(Collectors.toList());
    }

    private List<Food> filterByVegitarian(List<Food> foods, boolean isVegitarin) {
        return foods.stream().filter(food -> food.isVegitarin()==isVegitarin ).collect(Collectors.toList());
    }

    @Override
    public List<Food> searchFood(String keyword) {
        return foodRepository.searchFood(keyword);
    }

    @Override
    public Food findFoodById(Long foodId) throws Exception {
        Optional<Food> optionalFood = foodRepository.findById(foodId);
        if(optionalFood.isEmpty()){
            throw new Exception("food not exist...");
        }
        return optionalFood.get();
    }

    @Override
    public Food updateAvailabilityStatus(Long FoodId) throws Exception {
        Food food= findFoodById(FoodId);
        food.setAvailable(!food.isAvailable());
        return foodRepository.save(food);

    }
}
