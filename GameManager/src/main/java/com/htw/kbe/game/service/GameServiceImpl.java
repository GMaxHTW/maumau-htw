package com.htw.kbe.game.service;


import com.htw.kbe.card.card.export.ICardService;
import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.export.IGameService;
import com.htw.kbe.card.card.service.CardServiceImpl;
import com.htw.kbe.player.IPlayerService;
import com.htw.kbe.player.Player;
import com.htw.kbe.player.PlayerServiceImpl;
import com.htw.kbe.card.stack.export.IStackService;
import com.htw.kbe.card.stack.export.Stack;
import com.htw.kbe.card.stack.service.StackServiceImpl;
import com.htw.kbe.rules.service.export.IRulesService;

import java.util.List;


public class GameServiceImpl implements IGameService {

    private IStackService stackService = new StackServiceImpl();

    private ICardService cardService = new CardServiceImpl();

    private IRulesService rulesService;

    private IPlayerService playerService = new PlayerServiceImpl();


    @Override
    public Game createGame(List<Player> players) throws PlayerSizeInvalidException {

        if(players.size() < 2 ) {
            throw new PlayerSizeInvalidException("At least 2 players are needed to play MauMau");
        }

        Stack createdStack = stackService.createCardStack();
        Game game;
        game = new Game(players, createdStack);
        return game;
    }

    @Override
    public void switchActivePlayer(Game game) {
        List<Player> playerList = game.getPlayers();
        int indexOfLastPlayer = playerList.size();
        Player activePlayer = game.getActivePlayer();
        boolean gameDirection = game.isGameDirection();

        Player lastPlayerInList = playerList.get(indexOfLastPlayer-1);

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
