package com.htw.kbe.maumau.services;

import com.htw.kbe.maumau.interfaces.ICardService;
import com.htw.kbe.maumau.interfaces.IStackService;
import com.htw.kbe.maumau.model.Card;
import com.htw.kbe.maumau.model.Stack;

import java.util.List;

public class StackServiceImpl implements IStackService {

    private ICardService cardService = new CardServiceImpl();

    @Override
    public Stack createCardStack() {
        Stack createdStack = new Stack(cardService.getCards());
        return createdStack;
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
