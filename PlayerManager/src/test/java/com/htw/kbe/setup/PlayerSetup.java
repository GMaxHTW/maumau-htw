package com.htw.kbe.setup;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.player.export.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerSetup {

    public static Player createPlayer() {
        Player player = new Player("Testplayer");
        player.setHandCards(createHandCards());
        return player;
    }

    public static List<Card> createHandCards() {
        List<Card> createdHandCards = new ArrayList<>();
        Card clubAce = new Card(CardColor.CLUB, CardValue.ACE);
        Card diamondKing = new Card(CardColor.DIAMOND, CardValue.KING);
        Card heartKing = new Card(CardColor.HEART, CardValue.KING);
        createdHandCards.add(clubAce);
        createdHandCards.add(diamondKing);
        createdHandCards.add(heartKing);
        return createdHandCards;
    }



}
