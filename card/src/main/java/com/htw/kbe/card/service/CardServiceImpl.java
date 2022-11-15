package com.htw.kbe.card.service;


import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.card.export.ICardService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardServiceImpl implements ICardService {
    @Override
    public List<CardColor> getColors() {
        List<CardColor> cardColors = Arrays.asList(CardColor.values());
        return cardColors;
    }

    @Override
    public List<CardValue> getValues() {
        List<CardValue> cardColors = Arrays.asList(CardValue.values());
        return cardColors;
    }

    @Override
    public List<Card> getCards() {
        List<Card> cardList = new ArrayList<>();
        for (CardValue value : CardValue.values()) {
            for(CardColor color : CardColor.values()){
                cardList.add(new Card(color, value));
            }
        }
        return cardList;
    }
}
