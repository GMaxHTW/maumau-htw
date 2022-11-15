package com.htw.kbe.service;

import com.htw.kbe.export.Card;
import com.htw.kbe.export.CardStack;
import com.htw.kbe.export.ICardStackService;

import java.util.List;

public class CardStackService implements ICardStackService {

    @Override
    public CardStack createCardStack() {
        return null;
    }

    @Override
    public void shuffleCards(List<Card> cards) {

    }

    @Override
    public List<Card> drawCards(CardStack cardStack, int drawAmount) {
        return null;
    }

    @Override
    public List<Card> addCardToPlayedPile(CardStack cardStack, Card card) {
        return null;
    }

    @Override
    public Card setUpcard(CardStack cardStack) {
        return null;
    }

    @Override
    public Card setUpcard(CardStack cardStack, Card newCard) {
        return null;
    }

    @Override
    public List<Card> redoDrawPile(CardStack cardStack) {
        return null;
    }
}
