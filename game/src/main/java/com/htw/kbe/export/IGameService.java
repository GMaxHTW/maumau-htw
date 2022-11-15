package com.htw.kbe.export;

import export.Player;

import java.util.List;

public interface IGameService {

    /**
     * Creates a Game Instance
     * @param players list of Players
     * @param cardStack a stack of cards
     * @return     a game instance
     */
    Game createGame(List<Player> players, CardStack cardStack);
    /**
     * Changes a Game's active player according to the rules
     * @param game game instance to be updated
     */
    void switchActivePlayer(Game game);
    /**
     * Checks if the game is over
     * @param game game instance to be checked
     * @return     whether the game is over or not
     */
    boolean isGameOver(Game game);
    /**
     * Saves a game instance in the database
     * @param game game instance to be saved
     */
    void saveGame(Game game);
    /**
     * Deletes a game instance from the database
     * @param game game instance to be deleted
     */
    void deleteGame(Game game);
    /**
     * Retrieves a game from the database based on the id
     * @param id id of the game instance to be retrieved
     * @return     the game object with the corresponding id or null if not found
     */
    Game getSavedGame(Long id);

}
