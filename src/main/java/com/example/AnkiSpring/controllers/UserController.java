package com.example.AnkiSpring.controllers;

import com.example.AnkiSpring.domain.Cards;
import com.example.AnkiSpring.domain.User;
import com.example.AnkiSpring.services.CardsService;
import com.example.AnkiSpring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CardsService cardsService;

    @GetMapping("/testando")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/users")
    public ResponseEntity<Void> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/users/{userId}/cards")
    public ResponseEntity<Cards> createCardsForUser(@PathVariable Long userId, @RequestBody Cards cards) {
        User user = userService.getUserById(userId);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Cards createdCards = cardsService.createCards(user, cards);

        return new ResponseEntity<>(createdCards, HttpStatus.CREATED);
    }


    @DeleteMapping("/users/{userId}/cards/{cardsId}")
    public ResponseEntity<Void> deleteCardsForUser(@PathVariable Long userId, @PathVariable Long cardsId) {
        User user = new User();

        user.setId(userId);
        Cards cards = new Cards();

        cards.setId(cardsId);
        cardsService.deleteCards(user, cards);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{userId}/{cardId}/answerUser")
    public ResponseEntity<String> answerUser(@PathVariable Long userId,
                                             @PathVariable Long cardId,
                                             @RequestParam String answerUser) {
        User user = userService.getUserById(userId);
        Cards card = cardsService.getCardsById(cardId);

        if (user == null || card == null) {
            return new ResponseEntity<>("User or card not found", HttpStatus.NOT_FOUND);
        }

        try {
            card.setAnswerUser(answerUser);
            cardsService.updateCard(cardId, answerUser);
            return new ResponseEntity<>("User's answer updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }










}
