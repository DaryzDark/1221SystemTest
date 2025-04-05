package com._Systems.test.service;

import com._Systems.test.model.User;
import com._Systems.test.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser(User user) {
        double bmr = calculateDailyCalorieTarget(user);
        user.setDailyCalorieTarget(bmr);
        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id: " + id));
    }

    private double calculateDailyCalorieTarget(User user) {
        double bmr = 10 * user.getWeight() + 6.25 * user.getHeight() - 5 * user.getAge() + 5;

        return switch (user.getGoal()) {
            case LOSE_WEIGHT -> bmr - 300;
            case GAIN_WEIGHT -> bmr + 300;
            case MAINTAIN_WEIGHT -> bmr;
        };
    }
}

