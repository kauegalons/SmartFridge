package com.java10x.SmartFridge.service;

import com.java10x.SmartFridge.model.FoodItem;
import com.java10x.SmartFridge.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {

    private FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem save(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> list() {
        return foodItemRepository.findAll();
    }

    /*
    *   Do all the CRUD operations (
    *   Create,
    *   Read,
    *   Update,
    *   Delete) in this service class
     */
}
