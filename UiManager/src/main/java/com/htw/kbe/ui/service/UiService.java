package com.htw.kbe.ui.service;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.ui.export.IInputService;
import com.htw.kbe.ui.export.IUiService;



import java.util.*;

public class UiService implements IUiService {


    ICardService cardService;
    IInputService inputService;

    // Methoden für Game Message

    public UiService(ICardService cardService, IInputService inputService) {
        this.cardService = cardService;
        this.inputService = inputService;
    }


    @Override
    public void printWelcomeMessage() {
        System.out.println("Welcome to Mau Mau!");
    }

    @Override
    public void printGameStartMessage() {
        System.out.println("Starting the MauMau Game ");
    }

    @Override
    public void printUpCardMessage()  {
        System.out.println("The current up card is:  ");
    }

    @Override
    public void printErrorMessage(String errorMessage) {
        System.out.println("An error occurred with the following message: " + errorMessage);
    }

    @Override
    public void printGameDirection(boolean isClockwise) {
        if(isClockwise) {
            System.out.println("The game direction is clockwise");
        } else {
            System.out.println("The game direction is counter clockwise");
        }
    }

    @Override
    public void printWinnerMessage(Player player) {
        System.out.println("Player " + player.getUsername() + " has won the game");
    }

    @Override
    public void printDrawMessage(Player player, int drawCardsAmount) {
        System.out.println("The player " + player.getUsername() + " has to draw " + drawCardsAmount + " cards.");
    }

    @Override
    public void printPlayedCard(Player player, Card card) {
        System.out.println("The player " + player.getUsername() + " has played the card " + card.toString());
    }

    @Override
    public void printActivePlayer(Player player) {
        System.out.println("The current active player is: " + player);
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
             // TODO Sollte nicht rulesService prüfen --> canPlayCard?
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
    public void printCard(Card card) {
        cardService.printCard(card);
    }

    @Override
    public void printCardPlacing(Card fromCard, Card toCard) {
        cardService.printCardPlacing(fromCard, toCard);
    }


    @Override
    public void printHiddenCard() {
        cardService.printHiddenCard();
    }


    @Override
    public void printCardList(Collection<Card> cards) {
        cardService.printCardList(cards);
    }
}
