package com.htw.kbe.card.card.setup;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;

import java.util.ArrayList;
import java.util.List;

public class CardSetup {

    public static List<CardColor> cardColorList() {
        List<CardColor> cardColorList = new ArrayList<>();
        cardColorList.add(CardColor.Club);
        cardColorList.add(CardColor.Diamond);
        cardColorList.add(CardColor.Heart);
        cardColorList.add(CardColor.Spade);
        return cardColorList;
    }

    public static List<CardValue> cardValueList() {
        List<CardValue> cardColorList = new ArrayList<>();
        cardColorList.add(CardValue.Ace);
        cardColorList.add(CardValue.Seven);
        cardColorList.add(CardValue.Eight);
        cardColorList.add(CardValue.Nine);
        cardColorList.add(CardValue.Ten);
        cardColorList.add(CardValue.Jack);
        cardColorList.add(CardValue.Queen);
        cardColorList.add(CardValue.King);
        return cardColorList;
    }

    public static List<Card> gameCards() {
        List<Card> gameCards = new ArrayList<>();
        List<CardColor> cardColors = cardColorList();
        List<CardValue> cardValues = cardValueList();

        for (CardValue value : cardValues) {
            for (CardColor color: cardColors) {
                gameCards.add(new Card(color, value));
            }
        }
        return gameCards;
    }


    public static List<Card> cardListTesting() {
        List<Card> cardList = new ArrayList<>();
        cardList.add(new Card(CardColor.Heart, CardValue.Ace));
        cardList.add(new Card(CardColor.Spade, CardValue.Ace));
        cardList.add(new Card(CardColor.Club, CardValue.Ace));
        cardList.add(new Card(CardColor.Diamond, CardValue.Ace));

        cardList.add(new Card(CardColor.Heart, CardValue.Seven));
        cardList.add(new Card(CardColor.Heart, CardValue.Eight));
        cardList.add(new Card(CardColor.Heart, CardValue.Nine));
        cardList.add(new Card(CardColor.Heart, CardValue.Ten));
        cardList.add(new Card(CardColor.Heart, CardValue.Jack));
        cardList.add(new Card(CardColor.Heart, CardValue.Queen));
        cardList.add(new Card(CardColor.Heart, CardValue.King));

        cardList.add(new Card(CardColor.Spade, CardValue.Seven));
        cardList.add(new Card(CardColor.Spade, CardValue.Eight));
        cardList.add(new Card(CardColor.Spade, CardValue.Nine));
        cardList.add(new Card(CardColor.Spade, CardValue.Ten));
        cardList.add(new Card(CardColor.Spade, CardValue.Jack));
        cardList.add(new Card(CardColor.Spade, CardValue.Queen));
        cardList.add(new Card(CardColor.Spade, CardValue.King));

        return cardList;
    }

    public static Card getHeartKing() {
        return new Card(CardColor.Heart, CardValue.King);
    }





}
