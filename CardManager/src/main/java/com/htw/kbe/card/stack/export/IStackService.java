package com.htw.kbe.card.stack.export;

import com.htw.kbe.card.card.export.Card;

import java.util.List;

public interface IStackService {



    /**
     * Instantiates a CardStack object and sets its initial draw pile
     * @return      a CardStack instance
       */
    Stack createCardStack();

    /**
     * Shuffles CardStack's draw pile by mutating the list in place
     * @param  cards  the list of cards to be shuffled
     */
    void shuffleCards(List<Card> cards);


    /**
     * At game start & During the game:
     * Draws a number of cards from a card stack's draw pile
     * @param stack the card stack to be drawn from
     * @param drawAmount the number of cards the player is supposed to draw
     * @return       list of Cards of size equal to drawAmount
     */
    List<Card> drawCards(Stack stack, int drawAmount);

    /**
     * Adds a card to the cardStacks's played pile
     * @param stack the card stack to be updated
     * @param card the card to be added to the played pile
     * @return      the card added to the played pile
     */
    List<Card> addCardToPlayedPile(Stack stack, Card card);

    /**
     * At game start:
     * Takes the last card in the draw pile and sets it as the current upcard in the card stack
     * @param stack the card stack to be drawn from
     * @return      the current upcard of the cardstack
     */
    Card setUpcard(Stack stack);

    /**
     * During the game:
     * Sets a new card as the current upcard in the card stack and places it in the playedCards pile
     * @param stack the card stack to be drawn from
     * @param newCard the card
     * @return    the current upcard of the cardstack
     */
    Card setUpcard(Stack stack, Card newCard);

    /**
     * Should the drawPile be empty, it returns a newly shuffled drawPile
     * from the playedPile
     * @param stack the card stack to be drawn from
     * @return    newly reshuffled drawPile
     */
    List<Card> createDrawPile(Stack stack);


}
