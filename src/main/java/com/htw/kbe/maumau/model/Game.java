package com.htw.kbe.maumau.model;

import java.util.List;

public class Game {
    private Long id;
    private List<Player> players;
    private Player activePlayer;
    private CardStack cardStack;
    private boolean gameDirection;
    private int punishmentCardsCounter;
    private Card wishCard;

    public Game(List<Player> players, CardStack cardStack) {
        this.players = players;
        this.cardStack = cardStack;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getActivePlayer() {
        return activePlayer;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }

    public CardStack getCardStack() {
        return cardStack;
    }

    public void setCardStack(CardStack cardStack) {
        this.cardStack = cardStack;
    }

    public int getPunishmentCardsCounter() {
        return punishmentCardsCounter;
    }

    public void setPunishmentCardsCounter(int punishmentCardsCounter) {
        this.punishmentCardsCounter = punishmentCardsCounter;
    }

    public Card getWishCard() {
        return wishCard;
    }

    public void setWishCard(Card wishCard) {
        this.wishCard = wishCard;
    }

    public boolean isGameDirection() {
        return gameDirection;
    }

    public void setGameDirection(boolean gameDirection) {
        this.gameDirection = gameDirection;
    }
}
