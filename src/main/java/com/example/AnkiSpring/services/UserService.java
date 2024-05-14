package com.example.AnkiSpring.services;

import com.example.AnkiSpring.domain.User;
import com.example.AnkiSpring.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private final IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return iUserRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        User existingUser = iUserRepository.findByEmail(user.getEmail());

        if (existingUser != null) {
            throw new IllegalArgumentException("Email already exists");
        }

        User newUser = new User();

        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
        newUser.setPassword(user.getPassword());

        iUserRepository.save(newUser);

        return newUser;
    }


    @Override
    public void updateUser(User user) {
        Optional<User> existingUser = iUserRepository.findById(user.getId());

        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User updatedUser = existingUser.get();

        updatedUser.setName(user.getName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());

        iUserRepository.save(updatedUser);
    }

    @Override
    public void deleteUser(User user) {
        Optional<User> existingUser = iUserRepository.findById(user.getId());

        if (existingUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        User deletedUser = existingUser.get();

        iUserRepository.delete(deletedUser);
    }

    @Override
    public User getUserById(Long userId) {
        return iUserRepository.findById(userId)
                .orElse(null);
    }

}
