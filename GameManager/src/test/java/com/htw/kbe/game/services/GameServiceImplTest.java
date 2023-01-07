package com.htw.kbe.game.services;

import com.htw.kbe.card.card.export.ICardService;
import com.htw.kbe.card.stack.export.IStackService;
import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.service.GameServiceImpl;
import com.htw.kbe.game.setup.GameSetup;
import com.htw.kbe.player.export.IPlayerService;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.rule.export.IRulesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GameServiceImplTest {


    // Create services which are used inside a game



    @InjectMocks
    private GameServiceImpl gameService;
    @Mock
    GameSetup gameSetup;

    @InjectMocks
    IStackService stackService;

    @InjectMocks
    ICardService cardService;

    @InjectMocks
    IRulesService rulesService;

    @InjectMocks
    IPlayerService playerService;




    private List<Player> playerListValid;
    private List<Player> playerListInvalidAmount;
    private List<Player> playerListInvalidNames;

    private Game game;



    @BeforeEach
    public void setUp() {
        playerListValid = gameSetup.createPlayerListValid();
        playerListInvalidAmount = gameSetup.createPlayerListInvalidAmount();
        playerListInvalidNames = gameSetup.createPlayerListInvalidNaming();
        game = gameSetup.createGameNew();
    }


    @Test
    @DisplayName("Test if create game works with valid game object")
    void createValidGameStart() throws PlayerSizeInvalidException {
        Game game = gameService.createGame(gameSetup.createPlayerListValid());
        List<Player> playersGame = game.getPlayers();
        assertEquals(gameSetup.createPlayerListValid().size(), playersGame.size());
    }






    @Test
    @DisplayName("Tests if game with invalid playerlist is not created")
    void createInvalidGame() throws PlayerSizeInvalidException {
        Game game = gameService.createGame(gameSetup.createPlayerListInvalidAmount());
        assertEquals(null, game);
    }

    @Test
    @DisplayName("Tests if active player is switched by checking the usernames")
    void switchActivePlayerTestUsernames() throws PlayerSizeInvalidException {
        Game game = gameService.createGame(GameSetup.createPlayerListValid());
        Player firstActivePlayer = game.getActivePlayer();
        gameService.switchActivePlayer(game);
        Player secondActivePlayer = game.getActivePlayer();
        assertNotEquals(firstActivePlayer.getUsername(), secondActivePlayer.getUsername());
    }


    @Test
    @DisplayName("Checks if first user again after one round")
    void switchActivePlayerTestLogic() {
        Game game = GameSetup.createGameNew();

        int numOfPlayers = game.getPlayers().size();
        Player firstActivePlayer = game.getActivePlayer();

        for(int i = 0; i < numOfPlayers; i++) {
            gameService.switchActivePlayer(game);
            Player currentPlayer = game.getActivePlayer();
        }
        Player lastActivePlayer = game.getActivePlayer();

        assertEquals(true, firstActivePlayer.equals(lastActivePlayer));

    }

    @Test
    @DisplayName("Checks if game is over, should return true")
    void isGameOverValid() {
        Game game = gameSetup.createGameOver();
        boolean gameOver = gameService.isGameOver(game);
        assertEquals(true, gameOver);
    }

    @Test
    @DisplayName("Checks if game is over, should return false")
    void isGameOverInvalid() {
        Game game = gameSetup.createGameNew();
        boolean gameOver = gameService.isGameOver(game);
        assertEquals(false, gameOver);
    }

    // TODO: Implement tests when database is connected
//    @Test
//    @DisplayName("")
//    void saveGame() {
//        Game game = gameSetup.createGameNew();
//        gameService.saveGame(game);
//    }

    // TODO: Implement tests when database is connected
//    @Test
//    @DisplayName("")
//    void deleteGame() {
//        Game game = gameSetup.createGameNew();
//        gameService.deleteGame(game);
//    }

    // TODO: Implement tests when database is connected
//    @Test
//    @DisplayName("")
//    void getSavedGame() {
//
//    }
}