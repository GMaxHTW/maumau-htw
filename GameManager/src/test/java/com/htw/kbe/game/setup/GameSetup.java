package com.htw.kbe.game.setup;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.card.stack.export.Stack;


import java.util.*;

/**
 * Class created to setup Data for game
 */
public class GameSetup {

    public static Game createGameNew() {
        Game game = new Game(createPlayerListValid(), createStack());
        List<Player> players = game.getPlayers();
        List<Card> drawPile = game.getCardStack().getDrawPile();
        for(Player player : players) {
            List<Card> drawnCards = new ArrayList<>();
            for(int i = 0; i < 4; i++){
               drawnCards.add(drawPile.remove(0));
            }
            player.setHandCards(drawnCards);
        }
        return game;
    }

    public static Game createGameOngoing() {
        Game game = new Game(createPlayerListValid(), createStack());
        List<Player> allPlayers = game.getPlayers();



        return game;
    }

    public static Game createGameOver() {
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
            CardColor.CLUB,
            CardColor.DIAMOND,
            CardColor.HEART,
            CardColor.SPADE
    );

    public static List<CardValue> cardValues = Arrays.asList(
            CardValue.ACE,
            CardValue.KING,
            CardValue.QUEEN,
            CardValue.JACK,
            CardValue.TEN,
            CardValue.NINE,
            CardValue.EIGHT,
            CardValue.SEVEN
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
