package com.htw.kbe.service;

import com.htw.kbe.export.CardStack;
import com.htw.kbe.export.Game;
import com.htw.kbe.export.IGameService;
import export.Player;

import java.util.List;

public class GameServiceImpl implements IGameService {
    @Override
    public Game createGame(List<Player> players, CardStack cardStack) {
        return null;
    }

    @Override
    public void switchActivePlayer(Game game) {

    }

    @Override
    public boolean isGameOver(Game game) {
        return false;
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
