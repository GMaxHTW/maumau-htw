package com.htw.kbe.ui.export;

import com.htw.kbe.card.card.export.Card;

import java.util.Collection;
import java.util.List;

public interface IUiService {

    /**
     * Prints the welcome message of the game
     */
    void printWelcomeMessage();

    /**
     * Get the number of players wanted for the game
     * @return
     */
    int getNumberOfPlayers();

    // TODO: Do we want to name methods which return string to be called print?

    public List<String> getPlayerNames (int playersTotal);

    public String getNameOfPlayer();

    /**
     * Takes a card and returns a string which can be printed as a card to the console
     * @param card
     * @return
     */
    String printCard(Card card);


    /**
     * Takes two cards (fromCard & toCard) and
     * returns a string of both cards which can be printed to the console
     * @param fromCard
     * @param toCard
     * @return
     */
    public String printCardPlacing(Card fromCard, Card toCard);


    /**
     * Prints a hidden card (no value or color visible)
     * @return
     */
    public String printHiddenCard();

    /**
     * Takes a Collections of cards and
     * returns a string of all cards which can be printed to the console
     * @param cards
     * @return
     */
    public String printCardList(Collection<Card> cards);




}
