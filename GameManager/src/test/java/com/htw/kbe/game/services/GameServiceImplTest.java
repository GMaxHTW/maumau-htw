package com.htw.kbe.game.services;



import com.htw.kbe.card.card.export.ICardService;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.service.GameServiceImpl;
import com.htw.kbe.game.setup.GameSetup;
import com.htw.kbe.player.IPlayerService;
import com.htw.kbe.player.Player;
import com.htw.kbe.rules.service.export.IRulesService;
import com.htw.kbe.card.stack.export.IStackService;
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
    private ICardService cardService;
    @Mock
    private IPlayerService playerService;
    @Mock
    private IStackService stackService;
    @Mock
    private IRulesService rulesService;

    private List<Player> playerListValid;
    private List<Player> playerListInvalidAmount;
    private List<Player> playerListInvalidNames;

    private Game game;



    @BeforeEach
    public void setUp() {
        playerListValid = GameSetup.createPlayerListValid();
        playerListInvalidAmount = GameSetup.createPlayerListInvalidAmount();
        playerListInvalidNames = GameSetup.createPlayerListInvalidNaming();
        game = GameSetup.createGameNew();
    }


    @Test
    @DisplayName("Test if create game works with valid game object")
    void createValidGameStart() {
        Game game = gameService.createGame(GameSetup.createPlayerListValid());
        List<Player> playersGame = game.getPlayers();
        assertEquals(GameSetup.createPlayerListValid().size(), playersGame.size());
    }






    // TODO: Implement exception and check if Test throws exception
    @Test
    @DisplayName("Tets if game th")
    void createInvalidGame() {
        Game game = gameService.createGame(GameSetup.createPlayerListInvalidAmount());
        assertEquals(null, game);
    }

    @Test
    @DisplayName("Tests if active player is switched by checking the usernames")
    void switchActivePlayerTestUsernames() {
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
        Game game = GameSetup.createGameOver();
        boolean gameOver = gameService.isGameOver(game);
        assertEquals(true, gameOver);
    }

    @Test
    @DisplayName("Checks if game is over, should return false")
    void isGameOverInvalid() {
        Game game = GameSetup.createGameNew();
        boolean gameOver = gameService.isGameOver(game);
        assertEquals(false, gameOver);
    }

    @Test
    @DisplayName("")
    void saveGame() {
        Game game = GameSetup.createGameNew();
        gameService.saveGame(game);
    }

    @Test
    @DisplayName("")
    void deleteGame() {
        Game game = GameSetup.createGameNew();
        gameService.deleteGame(game);
    }

    @Test
    @DisplayName("")
    void getSavedGame() {

    }
}