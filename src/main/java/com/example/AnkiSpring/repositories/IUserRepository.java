package com.example.AnkiSpring.repositories;

import com.example.AnkiSpring.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(Long id);
    void deleteById(Long id);
    void createUser(User user);
    void updateUser(User user);
    void deleteUserById(Long id);
}
