package com.htw.kbe.game;



import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.card.service.CardServiceImpl;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.export.IGameService;
import com.htw.kbe.game.service.GameServiceImpl;
import com.htw.kbe.player.IPlayerService;
import com.htw.kbe.player.Player;
import com.htw.kbe.player.PlayerServiceImpl;
import com.htw.kbe.rules.service.export.IRulesService;
import com.htw.kbe.rules.service.service.RulesServiceImpl;
import com.htw.kbe.stack.service.export.IStackService;
import com.htw.kbe.stack.service.service.StackServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameServiceImplTest {


    // Create services which are used inside a game
    private IGameService gameService;
    private ICardService cardService;
    private IPlayerService playerService;
    private IStackService stackService;
    private IRulesService rulesService;

    private List<Player> validPlayerList = new ArrayList<>();
    private List<Player> invalidPlayerList = new ArrayList<>();


    @BeforeEach
    public void setUp() {
        this.gameService = new GameServiceImpl();
        this.cardService = new CardServiceImpl();
        this.playerService = new PlayerServiceImpl();
        this.stackService = new StackServiceImpl();
        this.rulesService = new RulesServiceImpl();
        this.fillValidPlayerList();
        this.fillInvalidPlayerList();
    }

    public void fillValidPlayerList() {
        Player player1 = new Player("Max");
        Player player2 = new Player("Tim");
        Player player3 = new Player("Hannah");
        validPlayerList.add(player1);
        validPlayerList.add(player2);
        validPlayerList.add(player3);
    }

    public void fillInvalidPlayerList() {
        Player player1 = new Player("Hannah");
        invalidPlayerList.add(player1);
    }




    @Test
    @DisplayName("Tests if created com.htw.kbe.game.export.Game contains same amount of players as testPlayerList")
    void createValidGame() {
        Game game = gameService.createGame(validPlayerList);
        List<Player> playersGame = game.getPlayers();
        assertEquals(validPlayerList.size(), playersGame.size());
    }


    // TODO: Implement exception and check if Test throws exception
    @Test
    @DisplayName("Tests if com.htw.kbe.game.export.Game is not created when not enough players in list")
    void createInvalidGame() {
        Game game = gameService.createGame(invalidPlayerList);
        assertEquals(null, game);
    }

    @Test
    @DisplayName("Tests if active player is switched by checking the usernames")
    void switchActivePlayerTestUsernames() {
        Game game = gameService.createGame(validPlayerList);
        Player firstActivePlayer = game.getActivePlayer();
        gameService.switchActivePlayer(game);
        Player secondActivePlayer = game.getActivePlayer();
        assertNotEquals(firstActivePlayer.getUsername(), secondActivePlayer.getUsername());
    }


    @Test
    @DisplayName("Checks if first user again after one round")
    void switchActivePlayerTestLogic() {
        Game game = gameService.createGame(validPlayerList);
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