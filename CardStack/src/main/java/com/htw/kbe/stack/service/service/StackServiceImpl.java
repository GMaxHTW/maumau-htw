package com.htw.kbe.stack.service.service;


import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.card.service.CardServiceImpl;
import com.htw.kbe.stack.service.export.IStackService;
import com.htw.kbe.stack.service.export.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StackServiceImpl implements IStackService {

    private ICardService cardService = new CardServiceImpl();


    @Override
    public Stack createCardStack() {
        Stack stack = new Stack();
        List<Card> createdDrawPile = redoDrawPile(stack);
        stack.setDrawPile(createdDrawPile);
        return stack;
    }

    // TODO: Brauchen wir die shuffleCards Methode?
    @Override
    public void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
    }

    @Override
    public List<Card> drawCards(Stack stack, int drawAmount) {
        List<Card> cardsToDraw = new ArrayList<>();
        List<Card> drawPile = stack.getDrawPile();
        for (int i = 0; i == drawAmount; i++) {
            cardsToDraw.add(drawPile.get(0));
            drawPile.remove(0) ;
        }
        return cardsToDraw;
    }

    @Override
    public List<Card> addCardToPlayedPile(Stack stack, Card card) {
        List<Card> playedCards = stack.getPlayedCards();
        playedCards.add(card);
        return playedCards;
    }


    // TODO: Wieso beide Methoden gleicher name?
    // 1) setFirstUpcard
    @Override
    public Card setUpcard(Stack stack) {
        Card upCard = stack.getDrawPile().get(0);
        stack.getPlayedCards().add(upCard);
        stack.setUpcard(upCard);
        return upCard;
    }

    // 2) updateUpCard
    @Override
    public Card setUpcard(Stack stack, Card newCard) {
        // TODO: Macht das so Sinn?
        Card newUpcard = newCard;
        stack.setUpcard(newCard);
        stack.getPlayedCards().add(newCard);
        return newUpcard;
    }

    @Override
    public List<Card> redoDrawPile(Stack stack) {
        List<Card> gameCards = new ArrayList<>();
        for(CardColor color : CardColor.values()) {
            for(CardValue value : CardValue.values()) {
                gameCards.add(new Card(color, value));
            }
        }
        Collections.shuffle(gameCards);
        return gameCards;
    }
}
