package com.htw.kbe.card.stack.export;

import com.htw.kbe.card.card.export.Card;

import java.util.ArrayList;
import java.util.List;

public class Stack {
    private Long id;
    private List<Card> drawPile;
    private List<Card> playedCards = new ArrayList<>();
    private Card upcard;

    // TODO: Add


    public Stack(){
        this.playedCards = new ArrayList<>();
        this.drawPile = new ArrayList<>();
    }
    public Stack(List<Card> drawPile){
        this.drawPile = drawPile;
    }

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

    public boolean isDrawPileEmpty(){
        return this.drawPile.isEmpty();
    }
}
