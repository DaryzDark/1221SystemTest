package com._Systems.test.service;

import com._Systems.test.model.User;
import com._Systems.test.repository.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserServiceTest {

    @Test
    void createUser_shouldCalculateCalorieTarget() {
        UserRepository mockRepo = mock(UserRepository.class);
        UserService userService = new UserService(mockRepo);

        User input = User.builder()
                .name("Alex")
                .email("alex@test.com")
                .age(30)
                .weight(80)
                .height(180)
                .goal(User.Goal.MAINTAIN_WEIGHT)
                .build();

        when(mockRepo.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User result = userService.createUser(input);

        double expectedBMR = 10 * 80 + 6.25 * 180 - 5 * 30 + 5;
        assertEquals(expectedBMR, result.getDailyCalorieTarget(), 0.1);
    }
}