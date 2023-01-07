package com.htw.kbe.ui.service;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.ui.export.IUiService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

@Service
public class UiService implements IUiService {


    @Override
    public void printWelcomeMessage() {
        System.out.println("Welcome to Mau Mau!");
    }

    @Override
    public int getNumberOfPlayers() {
        System.out.println("Please the number of player. (Between 2 and 4");
        Scanner scanner = new Scanner(System.in);

        int chosenNumber;

        while (true) {
            try {
                chosenNumber = Integer.parseInt(scanner.next());
                if(chosenNumber > 4 || chosenNumber < 2) {
                    System.out.println("Please choose a number between 2 and 4");
                }
                break;
            } catch (NumberFormatException e ) {
                System.out.println("Please type a number");
            }
        }
        return chosenNumber;
    }

    public List<String> getPlayerNames (int playersTotal) {

        List<String> playerNames = new ArrayList<>();

        for(int i = 0; i < playersTotal; i++) {
            System.out.println("Please select the name of the current Player\n" +
                    "The name has to have at least 2 letters and a maximum of 10 letters");
            String name = getNameOfPlayer();
            playerNames.add(name);
        }
        return playerNames;
    }

    public String getNameOfPlayer() {
        String name;
        Scanner scanner = new Scanner(System.in);

        while(true) {
            name = scanner.next();

            if(name.isBlank()) {
                System.out.println("The name is not allowed to be blank");
            } else if (name.length() < 3) {
                System.out.println("The name has to have at least 3 letters");
            } else if (name.length() > 10) {
                System.out.println("The name has to have a maximum of 10 letters");
            } else {
                break;
            }
        }

        return name;
    }


    @Override
    public String printCard(Card card) {
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


    @Override
    public String printCardPlacing(Card fromCard, Card toCard) {
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


    @Override
    public String printHiddenCard() {
        return "┌─────────┐\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "└─────────┘\n";
    }


    @Override
    public String printCardList(Collection<Card> cards) {
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




}
