package com.htw.kbe.player;

import com.htw.kbe.card.export.Card;

import java.util.ArrayList;
import java.util.List;

public class PlayerServiceImpl implements IPlayerService {
    @Override
    public List<Player> createPlayers(List<String> usernames) {
        List<Player> players = new ArrayList<>();
        for (String username: usernames){
            players.add(new Player(username));
        }
        return players;
    }

    @Override
    public void drawCards(Player player, Card card) {
        List<Card> handCards = player.getHandCards();
        handCards.add(card);
        player.setHandCards(handCards);
    }

    @Override
    public void playCard(Player player, Card card) {
        player.getHandCards().remove(card);
    }
}