package com.htw.kbe.game.services;



import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.service.GameServiceImpl;
import com.htw.kbe.game.setup.GameSetup;
import com.htw.kbe.player.IPlayerService;
import com.htw.kbe.player.Player;
import com.htw.kbe.rules.service.export.IRulesService;
import com.htw.kbe.stack.service.export.IStackService;
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
        game = GameSetup.createGame();
    }


    @Test
    @DisplayName("Tests if created com.htw.kbe.game.export.Game contains same amount of players as testPlayerList")
    void createValidGame() {
        Game game = gameService.createGame(GameSetup.createPlayerListValid());
        List<Player> playersGame = game.getPlayers();
        assertEquals(GameSetup.createPlayerListValid().size(), playersGame.size());
    }


    // TODO: Implement exception and check if Test throws exception
    @Test
    @DisplayName("Tests if com.htw.kbe.game.export.Game is not created when not enough players in list")
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
        Game game = gameService.createGame(GameSetup.createPlayerListValid());
        Player firstActivePlayer = game.getActivePlayer();
        gameService.switchActivePlayer(game);
        Player secondActivePlayer = game.getActivePlayer();
        gameService.switchActivePlayer(game);
        Player thirdActivePlayer = game.getActivePlayer();
        gameService.switchActivePlayer(game);
        Player forthActivePlayer = game.getActivePlayer();
        assertEquals(firstActivePlayer, forthActivePlayer);
    }

    @Test
    @DisplayName("")
    void isGameOver() {
    }

    @Test
    @DisplayName("")
    void saveGame() {
    }

    @Test
    @DisplayName("")
    void deleteGame() {
    }

    @Test
    @DisplayName("")
    void getSavedGame() {
    }
}