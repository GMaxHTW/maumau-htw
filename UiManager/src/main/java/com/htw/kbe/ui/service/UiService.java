package com.htw.kbe.ui.service;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.card.card.export.ICardService;
import com.htw.kbe.card.card.service.CardServiceImpl;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.ui.export.IInputService;
import com.htw.kbe.ui.export.IUiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UiService implements IUiService {


    ICardService cardService;

    IInputService inputService;

    // Methoden für Game Message

    @Autowired
    public UiService(ICardService cardService, IInputService inputService) {
        this.cardService = cardService;
        this.inputService = inputService;
    }


    @Override
    public void printWelcomeMessage() {
        System.out.println("Welcome to Mau Mau!");
    }

    @Override
    public void printStartMessage(Game game) {
        System.out.println("Starting the MauMau Game " + game.toString());
    }


    // Handling von User eingaben
    @Override
    public int getNumberOfPlayers() {
        return inputService.getNumberOfPlayers();
    }

    public List<String> getPlayerNames (int playersTotal) {
        return inputService.getPlayerNames(playersTotal);
    }

    public String getNameOfPlayer() {
        return inputService.getNameOfPlayer();
    }

    @Override
    public CardColor wishColor() {
        return inputService.wishColor();
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
        System.out.println("Select a number between 1 and " + sizeHandCards + " to play a card \nType 0 to draw a card");

        int indexSelectedCard;
        Scanner scanner = new Scanner(System.in);

        Card selectedCard = null;

        while(true) {
            indexSelectedCard = scanner.nextInt();

            if(indexSelectedCard < 0 || indexSelectedCard > sizeHandCards) {
                System.out.println("Number has to be between 0 and " + sizeHandCards);
            }
            // Wenn der Spieler Karte ziehen will wird null zurückgegeben
            if(indexSelectedCard == 0) return null;


             selectedCard = activeHandCards.get(indexSelectedCard - 1);

            if(!cardService.cardMatches(selectedCard, currentUpCard)) {
                System.out.println("The selected card " + selectedCard +" does not match with " + currentUpCard.toString());
            } else {
                break;
            }
        }
        return selectedCard;
    }




    // Card Printing


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
