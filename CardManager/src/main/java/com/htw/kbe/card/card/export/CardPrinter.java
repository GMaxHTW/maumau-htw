package com.htw.kbe.card.card.export;

import java.util.Collection;
import java.util.Map;

public class CardPrinter {

    public static String printCard(Card card) {
        String colorSign = card.getColor().toString();
        String valueSign = card.getValue().toString();
        String space = valueSign.length() > 1 ? "" : " ";

        return  "┌─────────┐\n" +
                "│ " + valueSign + space + "      │\n" +
                "│         │\n" +
                "│    " + colorSign + "   │\n" +
                "│         │\n" +
                "│      " + space + valueSign + " │\n" +
                "└─────────┘\n";
    }

    public static String printCardPlacing(Card fromCard, Card toCard) {
        String fromColorSign = fromCard.getColor().toString();
        String fromValueSign = fromCard.getValue().toString();;
        String fromSpace = fromValueSign.length() > 1 ? "" : " ";

        String toColorSign = toCard.getColor().toString();
        String toValueSign = toCard.getValue().toString();
        String toSpace = toValueSign.length() > 1 ? "" : " ";

        return  "┌─────────┐"                                + "     " + "┌─────────┐\n" +
                "│ " + fromValueSign + fromSpace + "      │" + "     " + "│ " + toValueSign + toSpace + "      │\n" +
                "│         │"                                + "     " + "│         │\n" +
                "│    " + fromColorSign + "    │"            + " --> " + "│    " + toColorSign + "    │\n" +
                "│         │"                                + "     " + "│         │\n" +
                "│      " + fromSpace + fromValueSign + " │" + "     " + "│      " + toSpace + toValueSign + " │\n" +
                "└─────────┘"                                + "     " + "└─────────┘\n";
    }

    public String printHiddenCard() {
        return "┌─────────┐\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "└─────────┘\n";
    }

    public static String printCardList(Collection<Card> cards) {
        StringBuilder sb = new StringBuilder();

        StringBuilder row1 = new StringBuilder();
        StringBuilder row2 = new StringBuilder();
        StringBuilder row3 = new StringBuilder();
        StringBuilder row4 = new StringBuilder();
        StringBuilder row5 = new StringBuilder();
        StringBuilder row6 = new StringBuilder();
        StringBuilder row7 = new StringBuilder();

        for (Card card : cards) {
            String colorSign = card.getColor().toString();
            String valueSign = card.getValue().toString();
            String space = valueSign.length() > 1 ? "" : " ";

            row1.append("┌─────────┐ ");
            row2.append("│ ").append(valueSign).append(space).append("      │ ");
            row3.append("│         │ ");
            row4.append("│    ").append(colorSign).append("    │ ");
            row5.append("│         │ ");
            row6.append("│      ").append(space).append(valueSign).append(" │ ");
            row7.append("└─────────┘ ");
        }

        return sb
                .append(row1).append("\n")
                .append(row2).append("\n")
                .append(row3).append("\n")
                .append(row4).append("\n")
                .append(row5).append("\n")
                .append(row6).append("\n")
                .append(row7).append("\n").toString();
    }

    public static String printCardMap(Map<String, Card> cardsMap) {
        StringBuilder result = new StringBuilder(printCardList(cardsMap.values()));

        for (String key : cardsMap.keySet()) {
            result.append("    (").append(key).append(")     ");
        }

        result.append("\n");

        return result.toString();
    }

}