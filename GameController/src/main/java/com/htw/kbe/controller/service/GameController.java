package com.htw.kbe.controller.service;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.controller.export.IGameController;
import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.service.GameServiceImpl;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.player.service.PlayerServiceImpl;
import com.htw.kbe.ui.export.IUiService;
import com.htw.kbe.ui.service.UiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.htw.kbe.game.export.IGameService;

import java.util.List;

@Component
public class GameController implements IGameController {


//    @Autowired
    private GameServiceImpl gameService;

//    @Autowired
    private PlayerServiceImpl playerService;

//    @Autowired
    private UiService uiService;

    private static Logger logger = LogManager.getLogger(GameController.class);

    public GameController() {
    }

    @Autowired
    public GameController(GameServiceImpl gameService, PlayerServiceImpl playerService, UiService uiService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.uiService = uiService;
    }


    @Override
    public void startApp() {
        // Show start message of the game
        uiService.printWelcomeMessage();

        try {
            Game game = initializeGame();
            gameService.giveStartingCards(game);
            uiService.printGameStartMessage();
            // TODO: Das kann man auch schöner machen
            uiService.printUpCardMessage();
            uiService.printCard(game.getCardStack().getUpCard());
            startGame(gameService, uiService, game);

        } catch (PlayerSizeInvalidException e) {
            logger.error("Exception was thrown in startApp method with the following message: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }


    private void startGame(IGameService gameService, IUiService uiService, Game game) throws PlayerSizeInvalidException {
        // Game loop
        while (true) {

//            try {
//
//
//            } catch (Exception e) {
//                logger.error("Exception was thrown in startGame method with the following message: {}", e.getMessage());
//                throw new RuntimeException(e);
//            }
            Player activePlayer = game.getActivePlayer();
            List<Card> activeHandCards = activePlayer.getHandCards();
            uiService.printActivePlayer(activePlayer);

            // Prüfen ob handsize größer 1 --> resetMau
            if(activeHandCards.size() > 1) {
                activePlayer.setSaidMau(false);
            }

            // Prüfen ob der Spieler passende Karten hat oder ziehen muss
            if(!gameService.hasMatchingCard(activeHandCards, game.getCardStack().getUpCard(), game.getWishedColor())) {
                // Player has no Matching card an hast to draw one
                uiService.printPlayerHasNoMatchingCardMessage(activePlayer);
                gameService.drawCard(activePlayer, game.getCardStack());
            } else {
                handlePlayerChoice(game);
            }


            // TODO: Prüfen ob game vorbei --> gameService
            if(gameService.isGameOver(game)) {
                // TODO replace System.out println
                uiService.printWinnerMessage(activePlayer);
                logger.info("The game is over. Player {} won the round", game.getActivePlayer());
                break;
            }

            if(game.isNextPlayerSkipped()) {
                // In case next player is skipped
                gameService.switchActivePlayer(game);
                uiService.printPlayerSkippedMessage(game.getActivePlayer());
                game.setNextPlayerSkipped(false);
            }
            gameService.switchActivePlayer(game);
        }

    }

    public void handlePlayerChoice(Game game) {
        while (true) {
            Player activePlayer = game.getActivePlayer();
            List<Card> activeHandCards = game.getActivePlayer().getHandCards();
            Card upCard = game.getCardStack().getUpCard();

            if(gameService.mustDraw(activePlayer, upCard)) {
                uiService.printDrawMessage(activePlayer, game.getDrawCardsCounter());
                for (int i = 0; i < game.getDrawCardsCounter(); i++) {
                    gameService.drawCard(activePlayer, game.getCardStack());
                }
            }
            // Player hat matching card und kann diese legen
            uiService.printCardList(activeHandCards);
            Card selectedCard = uiService.selectCardToPlay(activeHandCards, upCard);

            if (selectedCard.equals(null)) {
                logger.info("Player {} draws a card", activePlayer.getUsername());
                gameService.drawCard(activePlayer, game.getCardStack());
                break;

            }
            playerService.playCard(activePlayer, selectedCard);
            uiService.printCardPlacing(upCard, selectedCard);
            gameService.applyRules(game);
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
        return createdGame;
    }

}
