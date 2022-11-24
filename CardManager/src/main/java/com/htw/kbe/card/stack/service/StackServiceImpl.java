package com.htw.kbe.card.stack.service;


import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.ICardService;
import com.htw.kbe.card.card.service.CardServiceImpl;
import com.htw.kbe.card.stack.export.IStackService;
import com.htw.kbe.card.stack.export.Stack;

import java.util.Collections;
import java.util.List;

public class StackServiceImpl implements IStackService {

    private ICardService cardService = new CardServiceImpl();


    @Override
    public Stack createCardStack() {
        Stack stack = new Stack();
        List<Card> createdDrawPile = cardService.createCards();
        stack.setDrawPile(createdDrawPile);
        return stack;
    }

    @Override
    public void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
    }

    @Override
    public List<Card> drawCards(Stack stack, int drawAmount) {
        List<Card> drawPile = stack.getDrawPile();
        List<Card> cardsToDraw = drawPile.subList(0, drawAmount);
        stack.getDrawPile().removeAll(cardsToDraw);
        return cardsToDraw;
    }

    @Override
    public List<Card> addCardToPlayedPile(Stack stack, Card card) {
        List<Card> playedCards = stack.getPlayedCards();
        playedCards.add(card);
        return playedCards;
    }



    @Override
    public Card setFirstUpCard(Stack stack) {
        Card upCard = stack.getDrawPile().get(0);
        stack.getPlayedCards().add(upCard);
        stack.setUpCard(upCard);
        return upCard;
    }


    @Override
    public Card setNewUpCard(Stack stack, Card newCard) {
        // TODO: Wieso die Karte als return Wert der Funktion?
        stack.setUpCard(newCard);
        stack.getPlayedCards().add(newCard);
        return newCard;
    }


}
