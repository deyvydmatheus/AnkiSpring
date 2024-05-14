package com.example.AnkiSpring.services;

import com.example.AnkiSpring.domain.Cards;
import com.example.AnkiSpring.domain.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface ICardsService {
    Cards createCards(User id, Cards cards);

    void updateCards(User newUser, Cards newCards);

    void deleteCards(User id, Cards cards);

    Cards getCardsById(Long cardId);
    Cards updateCard(Long cardId, String answerUser);
}
