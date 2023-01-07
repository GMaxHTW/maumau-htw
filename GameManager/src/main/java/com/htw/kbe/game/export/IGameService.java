package com.htw.kbe.game.export;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.stack.export.Stack;
import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.player.Player;

import java.util.List;

public interface IGameService {

    /**
     * Creates a com.htw.kbe.game.export.Game Instance
     * @param players list of Players
     * @return     a game instance
     */
    public Game createGame(List<Player> players) throws PlayerSizeInvalidException;
    /**
     * Changes a com.htw.kbe.game.export.Game's active player according to the rules
     * @param game game instance to be updated
     * @return     the new active player
     */
    public void switchActivePlayer(Game game);
    /**
     * Checks if the game is over
     * @param game game instance to be checked
     * @return     whether the game is over or not
     */
    public boolean isGameOver(Game game);

    // TODO: Feeback abklären
    // es fehlen --> KarteZiehen, KarteSpielen, MauAnsage und FarbwunschAngeben
    // In Player --> KarteZiehen, KarteSpielen


    // TODO: Missing Methods (I) drawCard, (II) playCard, (III) saidMau, (IV) wishColor)

    /**
     * Adds a card to the Player's hand cards
     * Draws a number of cards from a card stack's draw pile
     * @param player the active Player
     * @param stack the card stack to be drawn from
     * @param drawAmount the number of cards the player is supposed to draw
     */
    public void drawCards(Player player, Stack stack, int drawAmount);



    /**
     * Removes a card from the Player's hand cards
     * @param player the active Player
     * @param card the card to be removed from the player's hand cards
     */
    public void playCard(Player player, Card card);

    // TODO: Beschreibung hinzufügen
    public void wishColor(CardColor cardColor, Game game);

    // TODO: Beschreibung hinzufügen
    public void saidMau(Player player);


    // TODO: Will be implemented when we integrate the database
    /**
     * Saves a game instance in the database
     * @param game game instance to be saved
     */
//    public void saveGame(Game game);
    /**
     * Deletes a game instance from the database
     * @param game game instance to be deleted
     */
//    public void deleteGame(Game game);
    /**
     * Retrieves a game from the database based on the id
     * @param id id of the game instance to be retrieved
     * @return     the game object with the corresponding id or null if not found
     */
//    public Game getSavedGame(Long id);

}
