package com.htw.kbe.ui.service;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.ui.export.IInputService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//@Service
public class InputService implements IInputService {

    public InputService() {
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

    @Override
    public List<String> getPlayerNames(int playersTotal) {
        List<String> playerNames = new ArrayList<>();

        for(int i = 0; i < playersTotal; i++) {
            System.out.println("Please select the name of the current Player\n" +
                    "The name has to have at least 2 letters and a maximum of 10 letters");
            String name = getNameOfPlayer();
            playerNames.add(name);
        }
        return playerNames;
    }

    @Override
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

        // TODO: Hack --> Has to be fixed
        CardColor selectedValue = CardColor.HEART;
        int indexSelectedValue;

        System.out.println("Select a color\n type 1 for CLUB \ntype 2 for DIAMOND \ntype 3  for HEART \ntype 4 for SPADE ");


        while(true) {
            indexSelectedValue = scanner.nextInt();
            if (indexSelectedValue < 0 && indexSelectedValue > 5) {
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
            case 4:
                selectedValue = CardColor.SPADE;
                break;
        }
        return selectedValue;
    }

    @Override
    public Card selectCardToPlay(List<Card> activeHandCards, Card currentUpCard) {
        return null;
    }
}
