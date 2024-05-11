package com.example.AnkiSpring.services;

import com.example.AnkiSpring.domain.Cards;
import com.example.AnkiSpring.domain.User;
import com.example.AnkiSpring.repositories.ICardsRepository;
import com.example.AnkiSpring.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void createCards(User newUser, Cards newCards) {
        Cards existingCards = iCardsRepository.findById(newCards.getId())
                .orElseThrow(() ->  new IllegalArgumentException("Cards not found"));


        existingCards.setTitleCard(newCards.getTitleCard());
        existingCards.setQuestion(newCards.getQuestion());
        existingCards.setAnswer(newCards.getAnswer());

        User existingUser = iUserRepository.findById(newUser.getId())
                .orElseThrow(() ->  new IllegalArgumentException("User not found"));


        existingCards.setOwner(existingUser);


        iCardsRepository.save(existingCards);
    }

    @Override
    public void updateCards(User newUser,Cards newCards) {
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
}
