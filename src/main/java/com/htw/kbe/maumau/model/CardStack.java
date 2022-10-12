package com.htw.kbe.maumau.model;

import java.util.ArrayList;
import java.util.List;

public class CardStack {
    private Long id;
    private List<Card> drawPile = new ArrayList<>();
    private List<Card> playedCards = new ArrayList<>();
    private List<Card> handCards = new ArrayList<>();
    private Card upcard;


    public CardStack(){}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Card> getDrawPile() {
        return drawPile;
    }

    public void setDrawPile(List<Card> drawPile) {
        this.drawPile = drawPile;
    }

    public List<Card> getPlayedCards() {
        return playedCards;
    }

    public void setPlayedCards(List<Card> playedCards) {
        this.playedCards = playedCards;
    }

    public Card getUpcard() {
        return upcard;
    }

    public void setUpcard(Card upcard) {
        this.upcard = upcard;
    }
}
