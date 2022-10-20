package com.htw.kbe.maumau.interfaces;
import com.htw.kbe.maumau.model.Card;
import com.htw.kbe.maumau.model.CardStack;

import java.util.List;

public interface ICardStackService {



    /**
     * Instantiates a CardStack object and sets its initial draw pile
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
     * Draws a number of cards from a card stack's draw pile
     * @param cardStack the card stack to be drawn from
     * @param drawAmount the number of cards the player is supposed to draw
     * @return       list of Cards of size equal to drawAmount
     */
    List<Card> drawCards(CardStack cardStack, int drawAmount);

    /**
     * Adds a card to the cardStacks's played pile
     * @param cardStack the card stack to be updated
     * @param card the card to be added to the played pile
     * @return      the card added to the played pile
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
