package com.htw.kbe.maumau.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
* for both Computer and Human Players ?
* */
public class Player {
    private Long id;
    private String username;
    private List<Card> handCards;
    private int saidMau = 0;

    public Player(String username) {
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.username = username;
     //   this.isRobot = isRobot;
        this.handCards = new ArrayList<>();

    }


    public int saidMau(){
        return saidMau++;
    }

    public int drawnCardsCounter(){
        return 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Card> getHandCards() {
        return handCards;
    }

    public void setHandCards(List<Card> handCards) {
        this.handCards = handCards;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", handCards=" + handCards +
                ", saidMau=" + saidMau +
                '}';
    }
}
