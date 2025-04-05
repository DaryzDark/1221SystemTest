package com._Systems.test.service;

import com._Systems.test.model.MealItem;
import com._Systems.test.repository.MealItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealItemService {

    private final MealItemRepository mealItemRepository;

    public MealItem save(MealItem item) {
        return mealItemRepository.save(item);
    }

    public List<MealItem> getAll() {
        return mealItemRepository.findAll();
    }
}