package com.htw.kbe.setup;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.player.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayerSetup {

    public static Player createPlayer() {
        Player player = new Player("Testplayer");
        player.setHandCards(createHandCards());
        return player;
    }

    public static List<Card> createHandCards() {
        List<Card> createdHandCards = new ArrayList<>();
        Card clubAce = new Card(CardColor.Club, CardValue.Ace);
        Card diamondKing = new Card(CardColor.Diamond, CardValue.King);
        Card heartKing = new Card(CardColor.Heart, CardValue.King);
        createdHandCards.add(clubAce);
        createdHandCards.add(diamondKing);
        createdHandCards.add(heartKing);
        return createdHandCards;
    }



}
