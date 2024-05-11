package com.example.AnkiSpring.services;

import com.example.AnkiSpring.domain.Cards;
import com.example.AnkiSpring.domain.User;
import org.springframework.stereotype.Service;

@Service
public interface ICardsService {
    void createCards(User id, Cards cards);
    void updateCards(User id, Cards cards);
    void deleteCards(User id, Cards cards);

}
