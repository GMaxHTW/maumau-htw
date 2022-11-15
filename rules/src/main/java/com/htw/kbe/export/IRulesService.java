package com.htw.kbe.export;

import com.htw.kbe.export.Card;
import export.Player;

public interface IRulesService {
    /**
     * Checks the Player's offered card against the current upcard
     * @param playerCard the offered card
     * @param currentUpcard the current upcard in stack
     * @return      whether the player's card choice is valid
     */
    boolean validatePlayerCard(Card playerCard, Card currentUpcard);

    /**
     * Checks if a card is a Jack, i.e.
     * a player can play any of the cards currently on hand
     * @param card card to check
     * @return      whether the checked card is a Jack
     */
    boolean cardIsJack(Card card);
    /**
     * Checks if a card is a Seven, i.e.
     * a player must draw cards
     * @param card card to check
     * @return      whether the checked card is a seven
     */
    boolean mustDrawCards(Card card);
    /**
     * Checks if a card is an Ace, i.e.
     * a player must sit out one round
     * @param card card to check
     * @return      whether the checked card is an Ace
     */
    boolean mustSitOneOut(Card card);
    /**
     * Checks if a card is a 9, i.e.
     * the direction of the game must be changed
     * @param card card to check
     * @return     the new game direction
     */
    boolean validateGameDirection(Card card);
    /**
     * Checks if a player's mau is valid
     * based on the number of cards currenly held
     * @param player player instance
     * @return     whether the player's mau is valid
     */
    boolean validateMau(Player player);
}

