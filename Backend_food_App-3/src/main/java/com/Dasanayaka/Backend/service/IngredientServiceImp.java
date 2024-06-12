package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.IngreadiantItem;
import com.Dasanayaka.Backend.model.IngredientCategory;
import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.repository.IngredientCategoryRepository;
import com.Dasanayaka.Backend.repository.IngredientItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientServiceImp implements  IngrediantService {
    private IngredientItemRepository ingredientItemRepository;
    private IngredientCategoryRepository ingredientCategoryRepository;

    private RestorentService restorentService;

    @Override
    public IngredientCategory createIngredientCategory(String name, Long restaurantId) throws Exception {

        Resturent resturent=restorentService.findRestorentById(restaurantId);
        IngredientCategory category= new IngredientCategory();
        category.setRestaurant(resturent);
        category.setName(name);
        return ingredientCategoryRepository.save(category);
    }

    @Override
    public IngredientCategory findIngredientCategory(Long id) throws Exception {
        Optional<IngredientCategory> opt= ingredientCategoryRepository.findById(id);
        if(opt.isEmpty()){
            throw  new Exception("Ingredient category not found");
        }
        return opt.get();
    }

    @Override
    public List<IngredientCategory> findIngredientCategoryByRestaurantId(Long Id) throws Exception {
        restorentService.findRestorentById(Id);
        return ingredientCategoryRepository.findByRestaurantId(Id);
    }

    @Override
    public IngreadiantItem createIngredientItem(Long restaurantId, String ingredientName, Long category) throws Exception {

        Resturent resturent=restorentService.findRestorentById(restaurantId);
        IngredientCategory ICategory=findIngredientCategory(category);

        IngreadiantItem ingreadiantItem= new IngreadiantItem();
        ingreadiantItem.setName(ingredientName);
        ingreadiantItem.setRestaurant(resturent);
        ingreadiantItem.setCategory(ICategory);
        IngreadiantItem ingredient = ingredientItemRepository.save(ingreadiantItem);
        ICategory.getIngredients().add(ingredient);

        return ingredient;
    }

    @Override
    public List<IngreadiantItem> findRestaurantsIngredients(Long restaurantId) {

        return ingredientItemRepository.findByRestaurantId(restaurantId);

    }

    @Override
    public IngreadiantItem updateStock(Long id) throws Exception {
        Optional<IngreadiantItem> optionalIngreadiantItem=ingredientItemRepository.findById(id);
        if(optionalIngreadiantItem.isEmpty()){
            throw new Exception("ingredient not found");
        }
        IngreadiantItem ingreadiantItem=optionalIngreadiantItem.get();
        ingreadiantItem.setInStoke(!ingreadiantItem.InStoke());
        return ingredientItemRepository.save(ingreadiantItem);
    }
}
