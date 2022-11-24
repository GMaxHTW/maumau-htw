package com.htw.kbe.game.setup;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.player.Player;
import com.htw.kbe.card.stack.export.Stack;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class created to setup Data for game
 */
public class GameSetup {


    public static Game createGame() {
        Game game = new Game(createPlayerListValid(), createStack());
        return game;
    }


    public static Stack createStack() {
        Stack stack = new Stack();
        List<Card> gameCards = generateCards();
        stack.setDrawPile(gameCards);
        return stack;
    }


    public static List<Card> generateCards() {
        List<Card> gameCards = new ArrayList<>();
        for(CardColor color : cardColors) {
            for(CardValue value : cardValues) {
                gameCards.add(new Card(color, value));
            }
        }
        return gameCards;
    }



    public static List<CardColor> cardColors = Arrays.asList(
            CardColor.Club,
            CardColor.Diamond,
            CardColor.Heart,
            CardColor.Spade
    );

    public static List<CardValue> cardValues = Arrays.asList(
            CardValue.Ace,
            CardValue.King,
            CardValue.Queen,
            CardValue.Jack,
            CardValue.Ten,
            CardValue.Nine,
            CardValue.Eight,
            CardValue.Seven
    );

    public static List<Player> createPlayerListValid() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Khalil"));
        playerList.add(new Player("Anna"));
        playerList.add(new Player("Max"));
        playerList.add(new Player("Philipp"));
        return playerList;
    }

    public static List<Player> createPlayerListInvalidAmount() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Khalil"));
        return playerList;
    }


    public static List<Player> createPlayerListInvalidNaming() {
        List<Player> playerList = new ArrayList<>();
        playerList.add(new Player("Khalil"));
        playerList.add(new Player("Anna"));
        playerList.add(new Player("Max"));
        playerList.add(new Player("Khalil"));
        return playerList;
    }


}
