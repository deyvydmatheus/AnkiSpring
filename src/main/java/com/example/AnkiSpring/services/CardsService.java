package com.example.AnkiSpring.services;

import com.example.AnkiSpring.domain.Cards;
import com.example.AnkiSpring.domain.User;
import com.example.AnkiSpring.repositories.ICardsRepository;
import com.example.AnkiSpring.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class CardsService implements ICardsService{

    private final ICardsRepository iCardsRepository;
    private final IUserRepository iUserRepository;

    @Autowired
    public CardsService(ICardsRepository iCardsRepository, IUserRepository iUserRepository) {
        this.iCardsRepository = iCardsRepository;
        this.iUserRepository = iUserRepository;
    }

    @Override
    public Cards createCards(User newUser, Cards newCards) {
        if (newUser == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        if (newCards == null) {
            throw new IllegalArgumentException("Cards cannot be null");
        }

        if (newCards.getTitleCard() == null || newCards.getTitleCard().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (newCards.getQuestion() == null || newCards.getQuestion().isEmpty()) {
            throw new IllegalArgumentException("Question cannot be null or empty");
        }
        if (newCards.getAnswer() == null || newCards.getAnswer().isEmpty()) {
            throw new IllegalArgumentException("Answer cannot be null or empty");
        }

        newCards.setOwner(newUser);

        Cards savedCards = iCardsRepository.save(newCards);

        return savedCards;
    }


    @Override
    public void updateCards(User newUser, Cards newCards) {
        if (newUser.getId() == null || newCards.getId() == null) {
            throw new IllegalArgumentException("Cards not found");
        }

        Cards existingCards = iCardsRepository.findById(newCards.getId())
                .orElseThrow(() -> new IllegalArgumentException("Cards not found"));

        existingCards.setTitleCard(newCards.getTitleCard());
        existingCards.setQuestion(newCards.getQuestion());
        existingCards.setAnswer(newCards.getAnswer());

        User existingUser = iUserRepository.findById(newUser.getId())
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));

        existingCards.setOwner(existingUser);

        iCardsRepository.save(existingCards);
    }

    @Override
    public void deleteCards(User newUser, Cards newCards) {
        if (newUser.getId() == null || newCards.getId() == null) {
            throw new IllegalArgumentException("Cards not found");
        }
        Cards existingCards = iCardsRepository.findById(newCards.getId())
                .orElseThrow(() ->  new IllegalArgumentException("Cards not found"));

        existingCards.setTitleCard(null);
        existingCards.setQuestion(null);
        existingCards.setAnswer(null);

        User existingUser = iUserRepository.findById(newUser.getId())
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));

        existingCards.setOwner(existingUser);

        iCardsRepository.delete(existingCards);

    }

    @Override
    public Cards getCardsById(Long cardId) {
        return iCardsRepository.findById(cardId)
                .orElse(null);
    }

    @Override
    public Cards updateCard(Long cardId, String answerUser) {
        LocalDate today = LocalDate.now();
        Optional<Cards> optionalCards = iCardsRepository.findById(cardId);

        if (optionalCards.isPresent()) {
            Cards card = optionalCards.get();

            card.setAnswerUser(answerUser);

            switch (answerUser) {
                case "Easy":
                    today = today.plusDays(7);
                    break;
                case "Mediu":
                    today = today.plusDays(5);
                    break;
                case "Hard":
                    today = today.plusDays(3);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid answer user");
            }
            card.setLastReviewDate(today);

            return iCardsRepository.save(card);
        } else {
            throw new IllegalArgumentException("Card not found with id " + cardId);
        }

    }


}
