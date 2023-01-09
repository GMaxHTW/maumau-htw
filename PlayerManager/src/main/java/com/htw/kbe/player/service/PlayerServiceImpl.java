package com.htw.kbe.player.service;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.player.export.IPlayerService;
import com.htw.kbe.player.export.Player;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
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
    public void drawCard(Player player, Card card) {
        List<Card> handCards = player.getHandCards();
        handCards.add(card);
        player.setHandCards(handCards);
    }




    @Override
    public void playCard(Player player, Card card) {
        player.getHandCards().remove(card);
    }


    // TODO: Logik einbauen --> Wenn player wieder mehr Karten saidMau --> false
    public void saidMau(Player player){
        player.setSaidMau(true);
    }

}
