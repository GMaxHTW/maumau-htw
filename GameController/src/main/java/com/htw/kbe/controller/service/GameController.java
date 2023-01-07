package com.htw.kbe.controller.service;

import com.htw.kbe.card.card.service.CardServiceImpl;
import com.htw.kbe.card.stack.export.Stack;
import com.htw.kbe.card.stack.service.StackServiceImpl;
import com.htw.kbe.controller.export.IGameController;
import com.htw.kbe.card.card.export.ICardService;
import com.htw.kbe.card.stack.export.IStackService;
import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.service.GameServiceImpl;
import com.htw.kbe.player.IPlayerService;
import com.htw.kbe.player.Player;
import com.htw.kbe.player.PlayerServiceImpl;
import com.htw.kbe.rules.service.export.IRulesService;
import com.htw.kbe.rules.service.service.RulesServiceImpl;
import com.htw.kbe.ui.export.IUiService;
import com.htw.kbe.ui.service.UiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.htw.kbe.game.export.IGameService;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GameController implements IGameController {


    private IGameService gameService = new GameServiceImpl();

//    @Autowired
    private IStackService stackService = new StackServiceImpl();

//    @Autowired
    private ICardService cardService = new CardServiceImpl();

//    @Autowired
    private IRulesService rulesService = new RulesServiceImpl();

//    @Autowired
    private IPlayerService playerService = new PlayerServiceImpl();

//    @Autowired
    private IUiService uiService = new UiService();


    private static Logger logger = LogManager.getLogger(GameController.class);


    public GameController() {
    }

    @Override
    public void startApp() {
        // Show start message of the game
        uiService.printWelcomeMessage();

        try {
            // Get the inital Game object with players and card stack
            Game game = initializeGame();


            // Start Game Loop
            // Endet wenn einer keine Karten mehr

            // Initialize Players with cards
            // TODO: implement dealingCards() --> GameService

            // Game Start message (Gameid --> players)

            // TODO: Erste Karte anzeigen


            // TODO: Starte mit erster Runde - Player index 0

            // TODO: Call Method startGame

            // Hat der Spieler eine Karte die er legen kann?

            // Ja --> Auswahl der Karten -- printCards --> selectCard()
            // Nein --> Muss Karte ziehen --> drawCard()

            // Special rules --> WishColor etc...


        } catch (PlayerSizeInvalidException e) {
            throw new RuntimeException(e);
        }

    }


    private void startGame(IGameService gameService, ICardService cardService, IUiService uiService, Game game) {
        // Eigentlicher Game loop
        while (true) {
            // TODO: Check game direction


            // TODO: Aktiver Player bestimmen --> game.getActivePlayer

            // TODO: Print active Player --> uiService

            // TODO: Prüfen ob handsize größer 1 --> resetMau

            // TODO: Prüfen ob der Spieler karten ziehen muss

            // TODO: Ja muss Karten ziehen --> handleDrawCards()

            // Nein muss keine Karten ziehen --> handle Players Turn

            // TODO: Prüfen ob game vorbei --> gameService


            // TODO: Suitwish --> uiService

            // TODO: Switch player


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
        System.out.println("Game has been created" + createdGame);
        return createdGame;
    }

}
