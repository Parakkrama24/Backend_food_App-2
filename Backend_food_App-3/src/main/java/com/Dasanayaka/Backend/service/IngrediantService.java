package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.IngreadiantItem;
import com.Dasanayaka.Backend.model.IngredientCategory;
import org.hibernate.graph.InvalidGraphException;

import java.util.List;

public interface IngrediantService {

    public IngredientCategory createIngredientCategory(String name,Long restaurantId) throws  Exception;

    public  IngredientCategory findIngredientCategory(Long id) throws  Exception;

    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long Id) throws  Exception;

    public IngreadiantItem createIngredientItem(Long restaurant , String ingredients, Long category) throws Exception ;

    public  List<IngreadiantItem> findRestaurantsIngredients(Long restaurantId);

    public  IngreadiantItem updateStock (Long id) throws Exception;
}
