package com.htw.kbe.game.service;


import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.card.service.CardServiceImpl;
import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.export.IGameService;
import com.htw.kbe.player.export.IPlayerService;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.player.service.PlayerServiceImpl;
import com.htw.kbe.rule.service.RulesServiceImpl;
import com.htw.kbe.stack.export.IStackService;
import com.htw.kbe.stack.export.Stack;
import com.htw.kbe.rule.export.IRulesService;
import com.htw.kbe.stack.service.StackServiceImpl;
import com.htw.kbe.ui.export.IUiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;

@Component
@Primary
public class GameServiceImpl implements IGameService {


    private IStackService stackService;
    private IRulesService rulesService;
    private IPlayerService playerService;

    private static Logger logger = LogManager.getLogger(GameServiceImpl.class);
    public GameServiceImpl() {

    }

    @Autowired
    public GameServiceImpl(IStackService stackService, IRulesService rulesService, IPlayerService playerService) {
        this.stackService = stackService;
        this.rulesService = rulesService;
        this.playerService = playerService;
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

        Player currentPlayer = game.getActivePlayer();
        List<Player> players = game.getPlayers();

        Player firstPlayer = players.get(0);
        Player lastPlayer = players.get(players.size()-1);

        boolean clockwise = game.isGameDirection();
        int currentIndex = players.indexOf(currentPlayer);
        int nextIndex;
        if (clockwise) {
            if(currentPlayer.equals(lastPlayer)) {
                nextIndex = 0;
            } else {
                nextIndex = currentIndex + 1;
            }
        } else {
            if(currentPlayer.equals(firstPlayer)) {
                nextIndex = players.size()-1;
            } else {
                nextIndex = currentIndex - 1;
            }

        }
        Player nextPlayer = players.get(nextIndex);

        game.setActivePlayer(nextPlayer);


//        List<Player> playerList = game.getPlayers();
//        int indexOfLastPlayer = playerList.size()-1;
//        Player activePlayer = game.getActivePlayer();
//        boolean gameDirection = game.isGameDirection();
//        Player lastPlayerInList = playerList.get(indexOfLastPlayer);
//
//        //
//
//
//        if(lastPlayerInList.equals(activePlayer) && gameDirection) {
//            Player nextActivePlayer = playerList.get(0);
//            game.setActivePlayer(nextActivePlayer);
//            logger.info("The next active player is: {}", nextActivePlayer);
//        } else{
//            int add = gameDirection ? 1 : -1;
//            Player nextActivePlayer = playerList.get(playerList.indexOf(activePlayer)+add);
//            game.setActivePlayer(nextActivePlayer);
//            logger.info("The next active player is: {}", nextActivePlayer);

//        }

    }

    @Override
    public boolean isGameOver(Game game) {
        return game.getActivePlayer().getHandCards().isEmpty();
    }

    @Override
    public void giveStartingCards(Game game) {
        // Each player gets 5 cards on the hand
        List<Player> players = game.getPlayers();
        Stack cardStack = game.getCardStack();
        int playerSize = players.size();

        for (int i = 0; i < playerSize; i++) {
            List<Card> drawCards = stackService.drawCards(cardStack, 5);
            players.get(i).setHandCards(drawCards);

//            for (int i = 0; i < 5; i++) {
//                Card drawnCard = stackService.drawCard(game.getCardStack());
//                playerService.drawCard(player, drawnCard);
//            }
            List<Card> createdHandCards = players.get(i).getHandCards();
            logger.info("Player {} has drawn inital cards. The cards are {}", players.get(i), createdHandCards);
        }
        stackService.setFirstUpCard(game.getCardStack());
        applyRules(game);
    }

    @Override
    public void drawCard(Player player, Stack stack) {
        Card drawCard = stack.getDrawPile().get(0);
        logger.info("The player {} has drawn the card {}", player, drawCard);
        playerService.drawCard(player, drawCard);
    }




    @Override
    public void playCard(Player player, Card card, Stack stack) {
        playerService.playCard(player, card);
        stackService.addCardToPlayedPile(stack, card);

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
        Player activePlayer = game.getActivePlayer();

        // 7 -> drawCardCounter +2
        if(rulesService.hasSeven(upCard)) {
            int drawCardsCounter = game.getDrawCardsCounter() +2;
            game.setDrawCardsCounter(drawCardsCounter);
        }
        // Jack -> Suit wish
        if(rulesService.canPlayAnyCard(upCard)) {
            // TODO uiService nur hier --> Sollte getrennt sein
            activePlayer.setCanWishColor(true);
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
    public List<Player> createPlayers(List<String> usernames) {
        return playerService.createPlayers(usernames);
    }

    @Override
    public boolean hasMatchingCard(List<Card> handCards, Card upCard, CardColor wishedColor) {
        boolean hasMatchingCard = false;
        for(Card card : handCards) {
            if(rulesService.validatePlayerCard(card, upCard, wishedColor)) {
                hasMatchingCard = true;
            }
        }
        return hasMatchingCard;
    }

    @Override
    public void resetColorWish(Game game) {
        game.setWishedColor(null);
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
