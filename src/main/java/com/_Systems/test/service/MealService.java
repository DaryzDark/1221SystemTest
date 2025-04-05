package com._Systems.test.service;

import com._Systems.test.model.Meal;
import com._Systems.test.model.User;
import com._Systems.test.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {

    private final MealRepository mealRepository;

    public Meal saveMeal(Meal meal) {
        return mealRepository.save(meal);
    }

    public List<Meal> getMealsByUser(User user) {
        return mealRepository.findAllByUser(user);
    }

    public List<Meal> getMealsByUserAndDate(User user, LocalDate date) {
        return mealRepository.findAllByUserAndDate(user, date);
    }
}
