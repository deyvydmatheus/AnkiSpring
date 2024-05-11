package com.example.AnkiSpring.repositories;

import com.example.AnkiSpring.domain.Cards;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICardsRepository extends JpaRepository<Cards, Long> {
}
