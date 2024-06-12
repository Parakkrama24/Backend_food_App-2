package com.Dasanayaka.Backend.service;

import com.Dasanayaka.Backend.model.Catagory;
import com.Dasanayaka.Backend.model.Resturent;
import com.Dasanayaka.Backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategorySerciceImp implements  CategoryService{

    @Autowired
        private  RestorentService restorentService;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Catagory CreateCategory(String name, Long userId) throws Exception {
        Resturent resturent= restorentService.findRestorentById(userId);
        Catagory catagory= new Catagory();
        catagory.setName(name);
        return categoryRepository.save(catagory);

    }

    @Override
    public List<Catagory> findCategoryByRestaurantId(Long id) throws Exception {
        return categoryRepository.findByRestaurantId(id);
    }

    @Override
    public Catagory findCategoryById(Long id) throws Exception {
        Optional<Catagory> optionalCatagory= categoryRepository.findById(id);
        if(optionalCatagory.isEmpty())
            throw  new Exception("category not found");

        return  optionalCatagory.get();
    }
}
