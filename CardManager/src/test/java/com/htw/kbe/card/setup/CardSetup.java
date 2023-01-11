package com.htw.kbe.card.setup;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;

import java.util.ArrayList;
import java.util.List;

public class CardSetup {

    public static List<CardColor> cardColorList() {
        List<CardColor> cardColorList = new ArrayList<>();
        cardColorList.add(CardColor.CLUB);
        cardColorList.add(CardColor.DIAMOND);
        cardColorList.add(CardColor.HEART);
        cardColorList.add(CardColor.SPADE);
        return cardColorList;
    }

    public static List<CardValue> cardValueList() {
        List<CardValue> cardColorList = new ArrayList<>();
        cardColorList.add(CardValue.ACE);
        cardColorList.add(CardValue.SEVEN);
        cardColorList.add(CardValue.EIGHT);
        cardColorList.add(CardValue.NINE);
        cardColorList.add(CardValue.TEN);
        cardColorList.add(CardValue.JACK);
        cardColorList.add(CardValue.QUEEN);
        cardColorList.add(CardValue.KING);
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
        cardList.add(new Card(CardColor.HEART, CardValue.ACE));
        cardList.add(new Card(CardColor.SPADE, CardValue.ACE));
        cardList.add(new Card(CardColor.CLUB, CardValue.ACE));
        cardList.add(new Card(CardColor.DIAMOND, CardValue.ACE));

        cardList.add(new Card(CardColor.HEART, CardValue.SEVEN));
        cardList.add(new Card(CardColor.HEART, CardValue.EIGHT));
        cardList.add(new Card(CardColor.HEART, CardValue.NINE));
        cardList.add(new Card(CardColor.HEART, CardValue.TEN));
        cardList.add(new Card(CardColor.HEART, CardValue.JACK));
        cardList.add(new Card(CardColor.HEART, CardValue.QUEEN));
        cardList.add(new Card(CardColor.HEART, CardValue.KING));

        cardList.add(new Card(CardColor.SPADE, CardValue.SEVEN));
        cardList.add(new Card(CardColor.SPADE, CardValue.EIGHT));
        cardList.add(new Card(CardColor.SPADE, CardValue.NINE));
        cardList.add(new Card(CardColor.SPADE, CardValue.TEN));
        cardList.add(new Card(CardColor.SPADE, CardValue.JACK));
        cardList.add(new Card(CardColor.SPADE, CardValue.QUEEN));
        cardList.add(new Card(CardColor.SPADE, CardValue.KING));

        return cardList;
    }

    public static Card getHeartKing() {
        return new Card(CardColor.HEART, CardValue.KING);
    }





}
