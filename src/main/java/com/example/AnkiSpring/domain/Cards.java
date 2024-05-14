package com.example.AnkiSpring.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titleCard;
    private String question;
    private String answer;

    @JsonIgnore
    private String answerUser;

    private LocalDate lastReviewDate;


    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public Cards() {
    }

    public Cards(Long id, String titleCard, String question, String answer, User owner, String answerUser, LocalDate lastReviewDate) {
        this.id = id;
        this.titleCard = titleCard;
        this.question = question;
        this.answer = answer;
        this.owner = owner;
        this.answerUser = answerUser;
        this.lastReviewDate = lastReviewDate;
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

    @JsonIgnore
    public String getAnswerUser() {
        return answerUser;
    }

    public void setAnswerUser(String answerUser) {
        this.answerUser = answerUser;
    }

    public LocalDate getLastReviewDate() {
        return lastReviewDate;
    }

    public void setLastReviewDate(LocalDate lastReviewDate) {
        this.lastReviewDate = lastReviewDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cards cards = (Cards) o;
        return Objects.equals(id, cards.id) && Objects.equals(titleCard, cards.titleCard) && Objects.equals(question, cards.question) && Objects.equals(answer, cards.answer) && Objects.equals(answerUser, cards.answerUser) && Objects.equals(lastReviewDate, cards.lastReviewDate) && Objects.equals(owner, cards.owner);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(id);
        result = 31 * result + Objects.hashCode(titleCard);
        result = 31 * result + Objects.hashCode(question);
        result = 31 * result + Objects.hashCode(answer);
        result = 31 * result + Objects.hashCode(answerUser);
        result = 31 * result + Objects.hashCode(lastReviewDate);
        result = 31 * result + Objects.hashCode(owner);
        return result;
    }
}
