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


    /**
     * Deals initial cards to players of the game
     * @param game game instance to get Player and Stack
     */
    void dealingCards(Game game);


    /**
     * Number of cards are drawn and given to the active player hand
     * @param game game instance to get active Player
     * @param amountOfCards amout of cards to draw
     */
    void giveDrawCardsToPlayer(Game game, int amountOfCards);

    // TODO: Feeback abklären
    // es fehlen --> KarteZiehen, KarteSpielen, MauAnsage und FarbwunschAngeben
    // In Player --> KarteZiehen, KarteSpielen


    // TODO: Missing Methods (I) drawCard, (II) playCard, (III) saidMau, (IV) wishColor)



    /**
     * Active player of the game placed card
     * @param game game instance to get active player
     * @param card the card to be removed from the player's hand cards
     */
    public void playCard(Game game, Card card);


    /**
     * Switches to the desired color
     * @param cardColor color of the card of wish
     * @param game game instance to get stack
     */
    public void wishColor(CardColor cardColor, Game game);


    /**
     * Set Mau to active player
     * @param game game instance to get active player
     */
    public void saidMau(Game game);


    /**
     * Reset mau auf active player
     * @param game game instance to get active player
     */
    void resetSaidMau(Game game);


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
