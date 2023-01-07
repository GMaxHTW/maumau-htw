package com.htw.kbe.game.export;



import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.card.stack.export.Stack;

import java.util.List;
import java.util.UUID;


public class Game {


    // Random ID as long
    private Long id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    private List<Player> players;
    private Player activePlayer;
    private Stack stack;

    // Initial auf true
    private boolean gameDirection = true;
    private int punishmentCardsCounter;

    private int drawCardsCounter = 0;

    private CardColor wishedColor;

    public Game(List<Player> players, Stack stack) {
        this.players = players;
        this.stack = stack;
        this.activePlayer = players.get(0);
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

    public CardColor getWishedColor() {
        return wishedColor;
    }

    public void setWishedColor(CardColor wishedColor) {
        this.wishedColor = wishedColor;
    }



    public boolean isGameDirection() {
        return gameDirection;
    }

    public void setGameDirection(boolean gameDirection) {
        this.gameDirection = gameDirection;
    }

    public int getDrawCardsCounter() {
        return drawCardsCounter;
    }

    public void setDrawCardsCounter(int drawCardsCounter) {
        this.drawCardsCounter = drawCardsCounter;
    }

}
