package com.example.AnkiSpring.services;

import com.example.AnkiSpring.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IUserService {
    List<User> getAllUsers();

    User createUser(User user);
    void updateUser(User user);
    void deleteUser(User user);

    User getUserById(Long userId);
}
