package com.htw.kbe.rule.export;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.player.export.Player;


public interface IRulesService {
    /**
     * Checks the Player's offered card against the current upcard
     * @param playerCard the offered card
     * @param currentUpcard the current upcard in stack
     * @return      whether the player's card choice is valid
     */
    public boolean validatePlayerCard(Card playerCard, Card currentUpcard, CardColor wishedColor);



    //TODO: muss doc hin
    public boolean checkLabelOrSuit(Card topCard, Card playedCard);

    public boolean checkIfSeven(Card topCard, Card playedCard);

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
    public boolean hasSeven(Card card);



    /**
     * check if a 7 has already been laid and the player wants to lay another one
     * @param player check if players has a 7 on his hand
     * @param topCard check if top card is a seven
     * @return whether a 7 lies on a 7
     */
    public boolean mustDraw(Player player, Card topCard);

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
     * Chekc if card is a 8
     * @param card card to Check
     * @return if player has to suspend
     */
    public boolean mustSuspend(Card card);
    /**
     * Checks if a player's mau is valid
     * based on the number of cards currenly held
     * @param player player instance
     * @return     whether the player's mau is valid
     */
    public boolean validateMau(Player player);
    // TODO: put validate method in game


    /**
     * Default value for to draw cards by a seven
     * @return int value of 2
     */
    public int NumberOfDrawnCardsBySeven();


    /**
     * Check if the top card is already a Jack
     * @param topCard Top card of the pile
     * @param playedCard card of the player
     * @return whether the move is valid
     */
    boolean jackOnJack(Card topCard, Card playedCard);

}