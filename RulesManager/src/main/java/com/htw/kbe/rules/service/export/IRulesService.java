package com.htw.kbe.rules.service.export;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.player.Player;
import com.htw.kbe.rules.service.exceptions.InvalidCardPlayedException;

public interface IRulesService {
    /**
     * Checks the Player's offered card against the current upcard
     * @param playerCard the offered card
     * @param currentUpcard the current upcard in stack
     * @return      whether the player's card choice is valid
     */
    public boolean validatePlayerCard(Card playerCard, Card currentUpcard, CardColor wishedColor) throws InvalidCardPlayedException;

    /**
     * Checks if a card is a Jack, i.e.
     * a player can play any of the cards currently on hand
     * @param card card to check
     * @return      whether the checked card is a Jack
     */
    public boolean canPlayAnyCard(Card card);
    /**
     * Checks if a card is a Seven, i.e.
     * a player must draw cards
     * @param card card to check
     * @return      whether the checked card is a seven
     */
    public boolean mustDrawCards(Card card);
    /**
     * Checks if a card is an Ace, i.e.
     * a player must sit out one round
     * @param card card to check
     * @return      whether the checked card is an Ace
     */
    public boolean mustSitOneOut(Card card);
    /**
     * Checks if a card is a 9, i.e.
     * the direction of the game must be changed
     * @param card card to check
     * @return     the new game direction
     */
    public boolean changeGameDirection(Card card);
    /**
     * Checks if a player's mau is valid
     * based on the number of cards currenly held
     * @param player player instance
     * @return     whether the player's mau is valid
     */
    public boolean validateMau(Player player);
    // TODO: put validate method in game


}
