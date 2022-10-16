package com.htw.kbe.maumau.interfaces;

import com.htw.kbe.maumau.model.CardStack;
import com.htw.kbe.maumau.model.Game;
import com.htw.kbe.maumau.model.Player;

import java.util.List;

public interface IGameService {

    /**
     * This is a Javadoc template
     * @param param param description
     * @return      return value description
     */

    public Game createGame(List<Player> players, CardStack cardStack);
    public Player switchPlayer(Player player);
    public Player getActivePlayer(Game game);
    public boolean isGameOver(Game game);
    public boolean saveGame(Game game);
    public Game getSavedGame(Long id);

}
