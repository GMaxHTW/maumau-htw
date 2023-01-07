package com.htw.kbe.player;

import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.stack.export.Stack;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayerServiceImpl implements IPlayerService {

    // TODO: Brauchen wir auch noch loggs, wenn die Methoden in Game aufgerufen werden und da schon entsprechender Logger ist??
    @Override
    public List<Player> createPlayers(List<String> usernames) {
        List<Player> players = new ArrayList<>();
        for (String username: usernames){
            players.add(new Player(username));
        }

        return players;
    }


    @Override
    public void drawCards(Player player, Stack stack, int drawAmount) {
        List<Card> handCards = player.getHandCards();
        List<Card> drawPile = stack.getDrawPile();
        List<Card> cardsToDraw = drawPile.subList(0, drawAmount);
        stack.getDrawPile().removeAll(cardsToDraw);
        handCards.addAll(cardsToDraw);
        player.setHandCards(handCards);
    }

    @Override
    public void playCard(Player player, Card card) {
        player.getHandCards().remove(card);
    }

    @Override
    public void saidMau(Player player){
        player.setSaidMau(true);
    }

    @Override
    public void resetSaidMau(Player player){
        player.setSaidMau(false);
    }

}
