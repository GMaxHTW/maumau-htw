package com.htw.kbe.ui.export;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.player.Player;

import java.util.Collection;
import java.util.List;

public interface IUiService {

    /**
     * Prints the welcome message of the game
     */
    void printWelcomeMessage();

    void printStartMessage(Game game);

    /**
     * Get the number of players wanted for the game
     * @return
     */
    int getNumberOfPlayers();

    // TODO: Do we want to name methods which return string to be called print?

    public List<String> getPlayerNames (int playersTotal);

    public String getNameOfPlayer();

    public CardColor wishColor();

    /**
     * Takes a card and returns a string which can be printed as a card to the console
     * @param card
     * @return
     */
    public void printCard(Card card);


    public void printActivePlayer(Player player);

    public Card selectCardToPlay(List<Card> activeHandCards, Card currentUpCard);

    /**
     * Takes two cards (fromCard & toCard) and
     * returns a string of both cards which can be printed to the console
     * @param fromCard
     * @param toCard
     * @return
     */
    public void printCardPlacing(Card fromCard, Card toCard);


    /**
     * Prints a hidden card (no value or color visible)
     * @return
     */
    public void printHiddenCard();

    /**
     * Takes a Collections of cards and
     * returns a string of all cards which can be printed to the console
     * @param cards
     * @return
     */
    public void printCardList(Collection<Card> cards);




}
