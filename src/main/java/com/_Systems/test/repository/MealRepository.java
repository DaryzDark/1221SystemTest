package com._Systems.test.repository;

import com._Systems.test.model.Meal;
import com._Systems.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    List<Meal> findAllByUser(User user);

    List<Meal> findAllByUserAndDate(User user, LocalDate date);
}
