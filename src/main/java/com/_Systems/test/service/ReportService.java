package com._Systems.test.service;

import com._Systems.test.model.Meal;
import com._Systems.test.model.User;
import com._Systems.test.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final MealRepository mealRepository;
    private final UserService userService;

    public DailyReport getDailyReport(Long userId, LocalDate date) {
        User user = userService.getUserById(userId);
        List<Meal> meals = mealRepository.findAllByUserAndDate(user, date);

        double totalCalories = meals.stream()
                .flatMap(meal -> meal.getItems().stream())
                .mapToDouble(item -> item.getDish().getCalories() * item.getServings())
                .sum();

        return new DailyReport(totalCalories, meals);
    }

    public boolean isWithinCalorieLimit(Long userId, LocalDate date) {
        double consumed = getDailyReport(userId, date).totalCalories();
        double limit = userService.getUserById(userId).getDailyCalorieTarget();
        return consumed <= limit;
    }

    public Map<LocalDate, Double> getCalorieHistory(Long userId) {
        User user = userService.getUserById(userId);
        List<Meal> allMeals = mealRepository.findAllByUser(user);

        return allMeals.stream()
                .collect(Collectors.groupingBy(
                        Meal::getDate,
                        Collectors.summingDouble(
                                meal -> meal.getItems().stream()
                                        .mapToDouble(item -> item.getDish().getCalories() * item.getServings())
                                        .sum()
                        )
                ));
    }

    public record DailyReport(double totalCalories, List<Meal> meals) {}
}
