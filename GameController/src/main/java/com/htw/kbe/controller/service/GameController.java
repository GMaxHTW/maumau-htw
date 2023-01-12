package com.htw.kbe.controller.service;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.controller.export.IGameController;
import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.ui.export.IUiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import com.htw.kbe.game.export.IGameService;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Objects;

@Controller
public class GameController implements IGameController {

    private IGameService gameService;
    private IUiService uiService;

    private static Logger logger = LogManager.getLogger(GameController.class);

    public GameController() {
    }

    @Autowired
    public GameController(IGameService gameService, IUiService uiService) {
        this.gameService = gameService;
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
            uiService.printErrorMessage(e.getMessage());
            logger.error("Exception was thrown in startApp method with the following message: {}", e.getMessage());
            throw new RuntimeException(e);
        }

    }

    @Override
    public void startGame(IGameService gameService, IUiService uiService, Game game)  {
        // Game loop
        while (true) {
            Player activePlayer = game.getActivePlayer();
            List<Card> activeHandCards = activePlayer.getHandCards();
            uiService.printActivePlayer(activePlayer);

            if(activeHandCards.size() > 1) {
                activePlayer.setSaidMau(false);
            }

            // Prüfen ob der Spieler passende Karten hat oder ziehen muss
            if(!gameService.hasMatchingCard(activeHandCards, game.getCardStack().getUpCard(), game.getWishedColor())) {
                uiService.printPlayerHasNoMatchingCardMessage(activePlayer);
                gameService.drawCard(activePlayer, game.getCardStack());
            }

            if (gameService.hasMatchingCard(activeHandCards, game.getCardStack().getUpCard(), game.getWishedColor())) {
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

    @Override
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
            uiService.printUpCardMessage();
            uiService.printCard(upCard);
            uiService.printCardList(activeHandCards);
            Card selectedCard = uiService.selectCardToPlay(activeHandCards, upCard);

            if (Objects.isNull(selectedCard)) {
                logger.info("Player {} draws a card", activePlayer.getUsername());
                uiService.printDrawMessage(activePlayer, 1);
                gameService.drawCard(activePlayer, game.getCardStack());
                break;

            }
            gameService.playCard(activePlayer, selectedCard, game.getCardStack());
            uiService.printCardPlacing(upCard, selectedCard);
            gameService.applyRules(game);

            // Player wants to wish
            if(activePlayer.isCanWishColor()) {
                CardColor wishedColor = uiService.wishColor();
                game.setWishedColor(wishedColor);
                activePlayer.setCanWishColor(false);
            } else {
                // TODO: Implement sayMau
                gameService.resetColorWish(game);
            }

            // Timer zum Mau sagen
            if (activePlayer.getHandCards().size() == 1) {

            }

            break;
        }
    }
    @Override
    public Game initializeGame () throws PlayerSizeInvalidException {
        int numberOfPlayers = uiService.getNumberOfPlayers();
        List<String> playerNames = uiService.getPlayerNames(numberOfPlayers);
        List<Player> playerList  = gameService.createPlayers(playerNames);
        Game createdGame = gameService.createGame(playerList);
        logger.info("Game {} has been created", createdGame);
        return createdGame;
    }

}
