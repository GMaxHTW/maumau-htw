package com.htw.kbe.ui.service;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.ICardService;
import com.htw.kbe.card.card.service.CardServiceImpl;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.ui.export.IUiService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UiService implements IUiService {


    ICardService cardService = new CardServiceImpl();

    @Override
    public void printWelcomeMessage() {
        System.out.println("Welcome to Mau Mau!");
    }

    @Override
    public void printStartMessage(Game game) {
        System.out.println("Starting the MauMau Game " + game.toString());
    }

    @Override
    public int getNumberOfPlayers() {
        System.out.println("Please the number of player. (Between 2 and 4)");
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
    public CardColor wishColor() {

        Scanner scanner = new Scanner(System.in);

        CardColor selectedValue;
        int indexSelectedValue;

        System.out.println("Select a color\n type 1 for CLUB \ntype 2 for DIAMOND \ntype 3  for HEART \ntype 4 for SPADE ");


        while(true) {
            indexSelectedValue = scanner.nextInt();
            if (indexSelectedValue > 0 && indexSelectedValue < 5) {
                System.out.println("The selected index must be between 1 and 4");
            } else {
                break;
            }
        }

        switch(indexSelectedValue){
            case 1:
                selectedValue = CardColor.CLUB;
                break;
            case 2:
                selectedValue = CardColor.DIAMOND;
                break;
            case 3:
                selectedValue = CardColor.HEART;
                break;
            default:
                selectedValue = CardColor.SPADE;
                break;
        }
        return selectedValue;
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

    public void printActivePlayer(Player player) {
        System.out.println("The current active player is: " + player);
    }

    @Override
    public Card selectCardToPlay(List<Card> activeHandCards, Card currentUpCard) {
        int sizeHandCards = activeHandCards.size();
        System.out.println("Select a number between 1 and " + sizeHandCards);

        int indexSelectedCard;
        Scanner scanner = new Scanner(System.in);

        Card selectedCard = null;

        while(true) {
            indexSelectedCard = scanner.nextInt();
            if(indexSelectedCard < 1 || indexSelectedCard > sizeHandCards) {
                System.out.println("Number has to be between 1 and " + sizeHandCards);
            }


             selectedCard = activeHandCards.get(indexSelectedCard - 1);

            if(!cardService.cardMatches(selectedCard, currentUpCard)) {
                System.out.println("The selected card " + selectedCard +" does not match with " + currentUpCard.toString());
            } else {
                break;
            }
        }
        return selectedCard;
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
            row2.append("│    ").append(valueSign).append(space).append(" │ ");
            row3.append("│         │ ");
            row4.append("│  ").append(colorSign).append("  │ ");
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
