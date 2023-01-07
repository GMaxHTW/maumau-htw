package com.htw.kbe.card.card.service;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.card.card.export.ICardService;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
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
    public List<Card> createCards() {
        List<Card> cardList = new ArrayList<>();
        for (CardValue value : CardValue.values()) {
            for(CardColor color : CardColor.values()){
                cardList.add(new Card(color, value));
            }
        }
        return cardList;
    }

    @Override
    public boolean cardMatches(Card card, Card compareCard) {
        return card.getValue() == compareCard.getValue() || card.getColor() == compareCard.getColor();
    }
}
