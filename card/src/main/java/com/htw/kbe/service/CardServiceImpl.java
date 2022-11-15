package com.htw.kbe.service;

import com.htw.kbe.maumau.interfaces.ICardService;
import com.htw.kbe.maumau.model.Card;
import com.htw.kbe.maumau.ui.CardColor;
import com.htw.kbe.maumau.ui.CardValue;

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
