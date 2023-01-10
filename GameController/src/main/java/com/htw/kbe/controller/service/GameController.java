package com.htw.kbe.controller.service;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.card.card.service.CardServiceImpl;
import com.htw.kbe.card.stack.service.StackServiceImpl;
import com.htw.kbe.controller.export.IGameController;
import com.htw.kbe.card.card.export.ICardService;
import com.htw.kbe.card.stack.export.IStackService;
import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.service.GameServiceImpl;
import com.htw.kbe.player.export.IPlayerService;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.player.service.PlayerServiceImpl;
import com.htw.kbe.ui.export.IUiService;
import com.htw.kbe.ui.service.UiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import com.htw.kbe.game.export.IGameService;

import javax.inject.Inject;
import java.util.List;

@Component
public class GameController implements IGameController {


//    @Autowired
    private GameServiceImpl gameService;

//    @Autowired
    private StackServiceImpl stackService;

//    @Autowired
    private CardServiceImpl cardService;

//    @Autowired
    private PlayerServiceImpl playerService;

//    @Autowired
    private UiService uiService;

    @Autowired
    public GameController(GameServiceImpl gameService, StackServiceImpl stackService, CardServiceImpl cardService, PlayerServiceImpl playerService, UiService uiService) {
        this.gameService = gameService;
        this.stackService = stackService;
        this.cardService = cardService;
        this.playerService = playerService;
        this.uiService = uiService;
    }




    private static Logger logger = LogManager.getLogger(GameController.class);


    public GameController() {
    }

    @Override
    public void startApp() {
        // Show start message of the game
        uiService.printWelcomeMessage();

        try {
            Game game = initializeGame();
            game.getPlayers();
            gameService.giveStartingCards(game);

            uiService.printStartMessage(game);

            stackService.setFirstUpCard(game.getCardStack());

            System.out.println("Current upcard is: ");

            uiService.printCard(game.getCardStack().getUpCard());

            startGame(gameService, cardService, uiService, game);

        } catch (PlayerSizeInvalidException e) {
            logger.error("Exception was thrown in startApp method with the following message: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }


    private void startGame(IGameService gameService, ICardService cardService, IUiService uiService, Game game) throws PlayerSizeInvalidException {
        // Game loop
        while (true) {

            try {
            } catch (Exception e) {
                logger.error("Exception was thrown in startGame method with the following message: {}", e.getMessage());
                throw new RuntimeException(e);
            }

            boolean currentGameDirection = game.isGameDirection();

            Player activePlayer = game.getActivePlayer();
            List<Card> activeHandCards = activePlayer.getHandCards();
            Card currentUpCard = game.getCardStack().getUpCard();

            uiService.printActivePlayer(activePlayer);
            // Muss Spieler Karten ziehen?


            // Prüfen ob handsize größer 1 --> resetMau
            if(activeHandCards.size() > 1) {
                activePlayer.setSaidMau(false);
            }


            // Prüfen ob der Spieler passende Karten hat oder ziehen muss
            boolean hasMatchingCard = gameService.hasMatchingCard(activeHandCards, game);

            if(!hasMatchingCard) {
                // Player has no Matching card an hast to draw one
                gameService.drawCard(activePlayer, game.getCardStack().getDrawPile().get(0));
            } else {
                handlePlayerChoice(game);
            }


            // TODO: Prüfen ob game vorbei --> gameService
            if(gameService.isGameOver(game)) {
                // TODO replace System.out println
                System.out.println("Player " + activePlayer.getUsername() + " has won the game");
                logger.info("The game is over. Player {} won the round", game.getActivePlayer());
                break;
            }

            if(game.isNextPlayerSkipped()) {
                // In case next player is skipped
                gameService.switchActivePlayer(game);
                game.setNextPlayerSkipped(false);
            }
            gameService.switchActivePlayer(game);
        }

    }

    public void handlePlayerChoice(Game game) throws PlayerSizeInvalidException {
        while (true) {
            Player activePlayer = game.getActivePlayer();
            List<Card> activeHandCards = game.getActivePlayer().getHandCards();
            Card upCard = game.getCardStack().getUpCard();

            if(gameService.mustDraw(activePlayer, upCard)) {
                for (int i = 0; i < game.getDrawCardsCounter(); i++) {
                    gameService.drawCard(activePlayer, game.getCardStack().getDrawPile().get(0));
                }
            }
            // Player hat matching card und kann diese legen
            uiService.printCardList(activeHandCards);
            Card selectedCard = uiService.selectCardToPlay(activeHandCards, upCard);

            if (selectedCard.equals(null)) {
                logger.info("Player {} draws a card", activePlayer.getUsername());
                gameService.drawCard(activePlayer, game.getCardStack().getDrawPile().get(0));
                break;

            }
            playerService.playCard(activePlayer, selectedCard);
            uiService.printCardPlacing(upCard, selectedCard);

            gameService.applyRules(game);


//            if(newUpCard.getValue().equals(CardValue.JACK)) {
//                CardColor wishedColor = uiService.wishColor();
//                game.setWishedColor(wishedColor);
//            }
            break;

        }
    }
    public Game initializeGame () throws PlayerSizeInvalidException {
        // Get number of players
        int numberOfPlayers = uiService.getNumberOfPlayers();

        // Get player names
        List<String> playerNames = uiService.getPlayerNames(numberOfPlayers);

        // Create list of Players with given names
        List<Player> playerList  = playerService.createPlayers(playerNames);

        // Create Game
        Game createdGame = gameService.createGame(playerList);
        logger.info("Game {} has been created", createdGame);
        System.out.println("Game has been created" + createdGame);
        return createdGame;
    }

}
