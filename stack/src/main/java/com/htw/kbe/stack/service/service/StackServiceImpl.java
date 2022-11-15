package com.htw.kbe.stack.service.service;


import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.card.service.CardServiceImpl;
import com.htw.kbe.stack.service.export.IStackService;
import com.htw.kbe.stack.service.export.Stack;

import java.util.List;

public class StackServiceImpl implements IStackService {

    private ICardService cardService = new CardServiceImpl();

    @Override
    public Stack createCardStack() {
        return null;
    }

    @Override
    public void shuffleCards(List<Card> cards) {

    }

    @Override
    public List<Card> drawCards(Stack stack, int drawAmount) {
        return null;
    }

    @Override
    public List<Card> addCardToPlayedPile(Stack stack, Card card) {
        return null;
    }

    @Override
    public Card setUpcard(Stack stack) {
        return null;
    }

    @Override
    public Card setUpcard(Stack stack, Card newCard) {
        return null;
    }

    @Override
    public List<Card> redoDrawPile(Stack stack) {
        return null;
    }
}
