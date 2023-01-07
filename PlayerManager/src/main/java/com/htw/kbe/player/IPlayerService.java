package com.htw.kbe.player;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.stack.export.Stack;

import java.util.List;

public interface IPlayerService {

    /**
     * Creates the Human and the Computer Players
     * @param usernames Player names
     * @return      the list of Players currently playing
     */
    public List<Player> createPlayers(List<String> usernames);
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
    public void saidMau(Player player);
}
