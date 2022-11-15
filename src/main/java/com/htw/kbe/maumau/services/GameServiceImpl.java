package com.htw.kbe.maumau.services;

import com.htw.kbe.maumau.interfaces.*;
import com.htw.kbe.maumau.model.Stack;
import com.htw.kbe.maumau.model.Game;
import com.htw.kbe.maumau.model.Player;

import java.util.List;

public class GameServiceImpl implements IGameService {

    private IStackService stackService = new StackServiceImpl();

    private ICardService cardService = new CardServiceImpl();

    private IRulesService rulesService;

    private IPlayerService playerService = new PlayerServiceImpl();

    @Override
    public Game createGame(List<Player> players) {
        if(players.size() < 2) {
            // TODO: create exception ivalidPlayersSize
            // TODO: Is there a max. amount of players for this game?
            return null;
        }

        Stack stack = stackService.createCardStack();
        Game game = new Game(players, stack);
        return game;
    }

    @Override
    public void switchActivePlayer(Game game) {
        List<Player> playerList = game.getPlayers();
        int indexOfLastPlayer = playerList.size();
        Player activePlayer = game.getActivePlayer();
        boolean gameDirection = game.isGameDirection();

        Player lastPlayerInList = playerList.get(indexOfLastPlayer);

        if(lastPlayerInList.equals(activePlayer) && gameDirection) {
            game.setActivePlayer(playerList.get(0));
        } else{
            game.setActivePlayer(playerList.get(playerList.indexOf(activePlayer) +1));
        }
    }

    @Override
    public boolean isGameOver(Game game) {
        return game.getActivePlayer().getHandCards().isEmpty();
    }

    @Override
    public void saveGame(Game game) {

    }

    @Override
    public void deleteGame(Game game) {

    }

    @Override
    public Game getSavedGame(Long id) {
        return null;
    }
}
