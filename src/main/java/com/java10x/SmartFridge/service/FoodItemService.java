package com.java10x.SmartFridge.service;

import com.java10x.SmartFridge.model.FoodItem;
import com.java10x.SmartFridge.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository) {
        this.foodItemRepository = foodItemRepository;
    }

    public FoodItem create(FoodItem foodItem) {
        return foodItemRepository.save(foodItem);
    }

    public List<FoodItem> list() {
        return foodItemRepository.findAll();
    }

    public Optional<FoodItem> findById(Long id) {
        return foodItemRepository.findById(id);
    }


    public void delete(FoodItem foodItem) {
        foodItemRepository.delete(foodItem);
    }

    public FoodItem update(Long id, FoodItem foodItem) {
        FoodItem saved = foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found with id: " + id));

        if(foodItem.getName() != null) {
            saved.setName(foodItem.getName());
        }
        if(foodItem.getCategory() != null) {
            saved.setCategory(foodItem.getCategory());
        }
        if(foodItem.getQuantity() != null) {
            saved.setQuantity(foodItem.getQuantity());
        }
        if (foodItem.getExpirationDate() != null) {
            saved.setExpirationDate(foodItem.getExpirationDate());
        }

        return foodItemRepository.save(saved);
    }
}
