package com.htw.kbe.maumau.interfaces;

import com.htw.kbe.maumau.model.Card;
import com.htw.kbe.maumau.model.CardStack;

import java.util.List;

public interface ICardStackService {



    /**
     * Instantiates a CardStack object and sets its initial draw pile
     * (32 - according to IRulesService?)
     * @return      a CardStack instance
       */
    CardStack createCardStack();

    /**
     * Shuffles CardStack's draw pile by mutating the list in place
     * @param  cards  the list of cards to be shuffled
     */
    void shuffleCards(List<Card> cards);


    /**
     * At game start & During the game:
     * Draws a number of cards from a card stack's draw pile and moves them to his/her handCards, i.e. card currently held by players
     * @param cardStack the card stack to be drawn from
     * @param drawAmount the number of cards the player is supposed to draw
     * @return      the list of Cards assigned to a Player
     */
    List<Card> drawCards(CardStack cardStack, int drawAmount);

    /**
     * Adds a card to the cardStacks's playedPile
     * @param cardStack the card stack to be updated
     * @param card the card tp be added to the playedPile
     * @return      the list of Cards assigned to a Player
     */
    List<Card> addCardToPlayedPile(CardStack cardStack,Card card);

    /**
     * At game start:
     * Takes the last card in the draw pile and sets it as the current upcard in the card stack
     * @param cardStack the card stack to be drawn from
     * @return      the current upcard of the cardstack
     */
    Card setUpcard(CardStack cardStack);

    /**
     * During the game:
     * Sets a new card as the current upcard in the card stack and places it in the playedCards pile
     * @param cardStack the card stack to be drawn from
     * @param newCard the card
     * @return    the current upcard of the cardstack
     */
    Card setUpcard(CardStack cardStack, Card newCard);

    /**
     * Should the drawPile be empty, it returns a newly shuffled drawPile
     * from the playedPile
     * @param cardStack the card stack to be drawn from
     * @return    newly reshuffled drawPile
     */
    List<Card> redoDrawPile(CardStack cardStack);


}
