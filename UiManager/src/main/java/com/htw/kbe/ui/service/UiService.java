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
