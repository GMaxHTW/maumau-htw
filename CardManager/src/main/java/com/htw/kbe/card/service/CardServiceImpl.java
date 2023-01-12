package com.htw.kbe.card.service;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.card.export.ICardService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@Primary
public class CardServiceImpl implements ICardService {


    public CardServiceImpl() {
    }

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

    @Override
    public void printCard(Card card) {
        String colorSign = card.getColor().toString();
        String valueSign = card.getValue().toString();
        String space = valueSign.length() > 1 ? "" : " ";

        System.out.println("┌─────────┐\n" +
                "│ " + valueSign + space + "    │\n" +
                "│         │\n" +
                "│    "+ colorSign + " │\n" +
                "│         │\n" +
                "│         │\n" +
                "└─────────┘\n");
    }

    @Override
    public void printCardPlacing(Card fromCard, Card toCard) {
        String fromColorSign = fromCard.getColor().toString();
        String fromValueSign = fromCard.getValue().toString();;
        String fromSpace = fromValueSign.length() > 1 ? "" : " ";

        String toColorSign = toCard.getColor().toString();
        String toValueSign = toCard.getValue().toString();
        String toSpace = toValueSign.length() > 1 ? "" : " ";

        System.out.println("┌─────────┐"                                + "     " + "┌─────────┐\n" +
                "│ " + fromValueSign + fromSpace + "      │" + "     " + "│ " + toValueSign + toSpace + "      │\n" +
                "│         │"                                + "     " + "│         │\n" +
                "│      " + fromColorSign + "│"            + " --> " + "  │     " + toColorSign + "│\n" +
                "│         │"                                + "     " + "│         │\n" +
                "│         │" + "     " + "│         │\n" +
                "└─────────┘"                                + "     " + "└─────────┘\n");

    }

    @Override
    public void printHiddenCard() {
        System.out.println("┌─────────┐\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "└─────────┘\n");

    }

    @Override
    public void printCardList(Collection<Card> cards) {
        StringBuilder sb = new StringBuilder();

        StringBuilder row1 = new StringBuilder();
        StringBuilder row2 = new StringBuilder();
        StringBuilder row3 = new StringBuilder();
        StringBuilder row4 = new StringBuilder();
        StringBuilder row5 = new StringBuilder();
        StringBuilder row6 = new StringBuilder();
        StringBuilder row7 = new StringBuilder();
        StringBuilder row8 = new StringBuilder();


        int cardCounter = 1;

        for (Card card : cards) {
            String colorSign = card.getColor().toString();
            String valueSign = card.getValue().toString();
            String space = valueSign.length() > 1 ? "" : " ";

            row1.append("┌─────────┐ ");
            row2.append("│    ").append(valueSign).append(space).append("│ ");
            row3.append("│         │ ");
            row4.append("│  ").append(colorSign).append("   │ ");
            row5.append("│         │ ");
            row6.append("│         │ ");
            row7.append("└─────────┘ ");
            row8.append("─ "+cardCounter + "───────┘");

            cardCounter++;
        }

        System.out.println(sb
                .append(row1).append("\n")
                .append(row2).append("\n")
                .append(row3).append("\n")
                .append(row4).append("\n")
                .append(row5).append("\n")
                .append(row6).append("\n")
                .append(row7).append("\n")
                .append(row8).append("\n").toString());
    }
}
