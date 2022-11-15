package com.htw.kbe.maumau.services;

import com.htw.kbe.maumau.interfaces.*;
import com.htw.kbe.maumau.model.Game;
import com.htw.kbe.maumau.model.Player;
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
    @DisplayName("Tests if created Game contains same amount of players as testPlayerList")
    void createValidGame() {
        Game game = gameService.createGame(validPlayerList);
        List<Player> playersGame = game.getPlayers();
        assertEquals(validPlayerList.size(), playersGame.size());
    }


    @Test
    @DisplayName("Tests if Game is not created when not enough players in list")
    void createInvalidGame() {
        Game game = gameService.createGame(invalidPlayerList);
        List<Player> playersGame = game.getPlayers();
        assertEquals(validPlayerList.size(), playersGame.size());
    }

    @Test
    @DisplayName("")
    void switchActivePlayer() {
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