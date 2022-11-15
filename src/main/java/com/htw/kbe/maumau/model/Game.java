package com.htw.kbe.maumau.model;

import java.awt.*;
import java.util.List;

public class Game {
    private Long id;
    private List<Player> players;
    private Player activePlayer;
    private Stack stack;

    // Initial auf true
    // --> TODO: Können noch überlegen ob wir das über rules anpassen wollen
    private boolean gameDirection = true;
    private int punishmentCardsCounter;
    private Color wishedColor;

    public Game(List<Player> players, Stack stack) {
        this.players = players;
        this.stack = stack;
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

    public Stack getCardStack() {
        return stack;
    }

    public void setCardStack(Stack stack) {
        this.stack = stack;
    }

    public int getPunishmentCardsCounter() {
        return punishmentCardsCounter;
    }

    public void setPunishmentCardsCounter(int punishmentCardsCounter) {
        this.punishmentCardsCounter = punishmentCardsCounter;
    }

    public Color getWishedColor() {
        return wishedColor;
    }

    public void setWishedColor(Color wishedColor) {
        this.wishedColor = wishedColor;
    }



    public boolean isGameDirection() {
        return gameDirection;
    }

    public void setGameDirection(boolean gameDirection) {
        this.gameDirection = gameDirection;
    }
}
