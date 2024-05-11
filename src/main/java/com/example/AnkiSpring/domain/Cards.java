package com.example.AnkiSpring.domain;

import jakarta.persistence.*;

import java.util.Objects;

public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleCard;
    private String question;
    private String answer;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Cards() {
    }

    public Cards(Long id, String titleCard, String question, String answer, User owner) {
        this.id = id;
        this.titleCard = titleCard;
        this.question = question;
        this.answer = answer;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleCard() {
        return titleCard;
    }

    public void setTitleCard(String titleCard) {
        this.titleCard = titleCard;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cards cards = (Cards) o;
        return Objects.equals(id, cards.id) && Objects.equals(titleCard, cards.titleCard) && Objects.equals(question, cards.question) && Objects.equals(answer, cards.answer) && Objects.equals(owner, cards.owner);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(titleCard);
        result = 31 * result + Objects.hashCode(question);
        result = 31 * result + Objects.hashCode(answer);
        result = 31 * result + Objects.hashCode(owner);
        return result;
    }
}
