package com.htw.kbe.card.card.export;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public interface ICardService {
    /**
     * Lists all possible card colors
     * @return      List with all Elements of CardColor
     */
    List<CardColor> getColors();
    /**
     * Lists all possible card values
     * @return      List with all Elements of CardValue
     */
    List<CardValue> getValues();
    /**
     * Creates all possible card combinations of CardColor and CardValue
     * @return      List of Cards in all possible combinations of Color and Value
     */
    List<Card> createCards();

    boolean cardMatches(Card card, Card compareCard);

    public void printCard(Card card);

    public void printCardPlacing(Card fromCard, Card toCard);

    public void printHiddenCard();

    public void printCardList(Collection<Card> cards);
}
