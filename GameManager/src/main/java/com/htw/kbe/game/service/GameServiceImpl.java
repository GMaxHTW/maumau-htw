package com.htw.kbe.game.service;


import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.ICardService;
import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.export.IGameService;
import com.htw.kbe.player.export.IPlayerService;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.card.stack.export.IStackService;
import com.htw.kbe.card.stack.export.Stack;
import com.htw.kbe.rule.export.IRulesService;
import com.htw.kbe.ui.export.IUiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements IGameService {

    private IStackService stackService;
    private ICardService cardService;
    private IRulesService rulesService;
    private IPlayerService playerService;

    private IUiService uiService;
    private static Logger logger = LogManager.getLogger(GameServiceImpl.class);

    public GameServiceImpl() {

    }

    @Autowired
    public GameServiceImpl(IStackService stackService, ICardService cardService, IRulesService rulesService, IPlayerService playerService, IUiService uiService) {
        this.stackService = stackService;
        this.cardService = cardService;
        this.rulesService = rulesService;
        this.playerService = playerService;
        this.uiService = uiService;
    }

    @Override
    public Game createGame(List<Player> players) throws PlayerSizeInvalidException {

        if(players.size() < 2 ) {
            logger.error("At least 2 players are needed to play MauMau");
            throw new PlayerSizeInvalidException("Invalid number of players");
        }

        Stack createdStack = stackService.createCardStack();
        stackService.shuffleCards(createdStack.getDrawPile());
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
    public boolean isGameOver(Game game) {
        return game.getActivePlayer().getHandCards().isEmpty();
    }

    @Override
    public void giveStartingCards(Game game) {
        // Each player gets 5 cards on the hand
        List<Player> players = game.getPlayers();

        for (Player player : players) {
            for (int i = 0; i < 5; i++) {
                Card drawnCard = stackService.drawCard(game.getCardStack());
                playerService.drawCard(player, drawnCard);
            }
            List<Card> createdHandCards = player.getHandCards();
            logger.info("Player {} has drawn inital cards. The cards are {}", player, createdHandCards);
        }
    }

    // TODO: Sollte die Methode nicht drawCard heißen --> Ist a immer nur eine
    @Override
    public void drawCard(Player player, Card card) {
        logger.info("The player {} has drawn the card {}", player, card);
        playerService.drawCard(player, card);
    }




    @Override
    public void playCard(Player player, Card card) {
        playerService.playCard(player, card);
    }

    @Override
    public void wishColor(CardColor cardColor, Game game) {
        game.setWishedColor(cardColor);

    }

    @Override
    public void saidMau(Player player) {
        playerService.saidMau(player);
    }


    public boolean mustDraw (Player activePlayer, Card upCard) {
        return rulesService.mustDraw(activePlayer, upCard);
    }


    @Override
    public void applyRules(Game game) {
        // Get top card
        Card upCard = game.getCardStack().getUpCard();

        // 7 -> drawCardCounter +2
        if(rulesService.hasSeven(upCard)) {
            int drawCardsCounter = game.getDrawCardsCounter() +2;
            game.setDrawCardsCounter(drawCardsCounter);
        }
        // Jack -> Suit wish
        if(rulesService.canPlayAnyCard(upCard)) {
            CardColor wishedColor = uiService.wishColor();
            game.setWishedColor(wishedColor);
        }
        // 9 -> Change direction of game
        if(rulesService.changeGameDirection(upCard)) {
            game.setGameDirection(!game.isGameDirection());
        }
        // 8 -> der nächste spieler aussetzen
        if(rulesService.mustSuspend(upCard)){
            game.setNextPlayerSkipped(true);
        }

    }

    @Override
    public boolean hasMatchingCard(List<Card> handCards, Game game) {
        boolean hasMatchingCard = false;
        Card upCard = game.getCardStack().getUpCard();
        CardColor wishedColor = game.getWishedColor();

        for(Card card : handCards) {
            if(rulesService.validatePlayerCard(card, upCard, wishedColor)) {
                hasMatchingCard = true;
            }
        }
        return hasMatchingCard;

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
