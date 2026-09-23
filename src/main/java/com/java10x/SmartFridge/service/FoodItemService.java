package com.java10x.SmartFridge.service;

import com.java10x.SmartFridge.dto.FoodDTO;
import com.java10x.SmartFridge.mapper.FoodMapper;
import com.java10x.SmartFridge.model.FoodItem;
import com.java10x.SmartFridge.repository.FoodItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    private final FoodItemRepository foodItemRepository;
    private final FoodMapper foodMapper;

    public FoodItemService(FoodItemRepository foodItemRepository, FoodMapper foodMapper) {
        this.foodItemRepository = foodItemRepository;
        this.foodMapper = foodMapper;
    }

    public FoodDTO createFood(FoodDTO food) {
        FoodItem foodItem = foodMapper.map(food);
        foodItem = foodItemRepository.save(foodItem);
        return foodMapper.map(foodItem);
    }

    public List<FoodDTO> listAllFood() {

        return foodItemRepository.findAll()
                .stream()
                .map(foodMapper::map)
                .toList();

    }

    public FoodDTO findById(Long id) {
        return foodItemRepository.findById(id)
                .map(foodMapper::map)
                .orElse(null);
    }


    public boolean deleteFood(Long id) {
        if (!foodItemRepository.existsById(id)) {
            return false;
        }
        foodItemRepository.deleteById(id);
        return true;
    }

    public FoodDTO updateFood(Long id, FoodDTO food) {
         Optional<FoodItem> found = foodItemRepository.findById(id);
         if (found.isEmpty()) {
            return  null;
         }

         FoodItem saved = found.get();

         if(food.getName() != null) {
             saved.setName(food.getName());
         }
         if(food.getCategory() != null) {
             saved.setCategory(food.getCategory());
         }
         if(food.getQuantity() != null) {
             saved.setQuantity(food.getQuantity());
         }
         if (food.getExpirationDate() != null) {
             saved.setExpirationDate(food.getExpirationDate());
         }

         return foodMapper.map(foodItemRepository.save(saved));

    }
}
