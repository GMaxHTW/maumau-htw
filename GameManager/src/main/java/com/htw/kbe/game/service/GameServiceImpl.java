package com.htw.kbe.game.service;


import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.ICardService;
import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.export.IGameService;
import com.htw.kbe.card.card.service.CardServiceImpl;
import com.htw.kbe.player.IPlayerService;
import com.htw.kbe.player.Player;
import com.htw.kbe.player.PlayerServiceImpl;
import com.htw.kbe.card.stack.export.IStackService;
import com.htw.kbe.card.stack.export.Stack;
import com.htw.kbe.card.stack.service.StackServiceImpl;
import com.htw.kbe.rules.service.export.IRulesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GameServiceImpl implements IGameService {

    @Autowired
    private IStackService stackService;

    @Autowired
    private ICardService cardService;

    @Autowired
    private IRulesService rulesService;

    @Autowired
    private IPlayerService playerService = new PlayerServiceImpl();

    private static Logger logger = LogManager.getLogger(GameServiceImpl.class);



    @Override
    public Game createGame(List<Player> players) throws PlayerSizeInvalidException {

        if(players.size() < 2 ) {
            logger.error("At least 2 players are needed to play MauMau");
            throw new PlayerSizeInvalidException("Invalid number of players");
        }

        Stack createdStack = stackService.createCardStack();
        Game game;
        game = new Game(players, createdStack);
        logger.info("Created new game: {}", game);
        return game;
    }

    // TODO: Refactor method to make it more smart and readable
    @Override
    public void switchActivePlayer(Game game) {
        List<Player> playerList = game.getPlayers();
        int indexOfLastPlayer = playerList.size();
        Player activePlayer = game.getActivePlayer();
        boolean gameDirection = game.isGameDirection();
        Player lastPlayerInList = playerList.get(indexOfLastPlayer-1);

        if(lastPlayerInList.equals(activePlayer) && gameDirection) {
            Player nextActivePlayer = playerList.get(0);
            game.setActivePlayer(playerList.get(0));
            logger.info("The next active player is: {}", nextActivePlayer);
        } else{
            Player nextActivePlayer = playerList.get(playerList.indexOf(activePlayer) +1);
            game.setActivePlayer(playerList.get(playerList.indexOf(activePlayer) +1));
            logger.info("The next active player is: {}", nextActivePlayer);

        }

    }


    @Override
    public void dealingCards(Game game){
        Stack stack = game.getCardStack();
        for(Player player : game.getPlayers()){
            playerService.drawCards(player, stack, stack.getNumberOfInitialCards());
        }
    }


    @Override
    public void giveDrawCardsToPlayer(Game game, int amountOfCards){
        Player activePlayer = game.getActivePlayer();
        Stack stack = game.getCardStack();
        playerService.drawCards(activePlayer, stack, amountOfCards);
    }



    @Override
    public boolean isGameOver(Game game) {
        logger.info("Game ends");
        return game.getActivePlayer().getHandCards().isEmpty();

    }

    // TODO: Sollte die Methode nicht drawCard heißen --> Ist a immer nur eine
    @Override
    public void drawCards(Player player, Stack stack, int drawAmount) {
        logger.info("Player {} has to draw {} cards", player.getUsername(), drawAmount);
        playerService.drawCards(player, stack, drawAmount);

    }



    @Override
    public void playCard(Player player, Card card) {
        logger.info("Player {} draw {}", player.getUsername(), card);
        playerService.playCard(player, card);
    }

    @Override
    public void wishColor(CardColor cardColor, Game game) {
        logger.info("Players color wish is {}", cardColor);
        game.setWishedColor(cardColor);

    }

    @Override
    public void saidMau(Player player) {
        logger.info("Player {} said Mau", player.getUsername());
        playerService.saidMau(player);
    }


    // TODO: Will be implemented when we integrate the database
//    @Override
//    public void saveGame(Game game) {
//
//    }
//
//    @Override
//    public void deleteGame(Game game) {
//
//    }
//
//    @Override
//    public Game getSavedGame(Long id) {
//        return null;
//    }
}
