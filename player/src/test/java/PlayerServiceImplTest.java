package com.htw.kbe.maumau.services;

import com.htw.kbe.maumau.interfaces.IPlayerService;
import com.htw.kbe.maumau.model.Card;
import com.htw.kbe.maumau.model.Player;
import com.htw.kbe.maumau.ui.CardColor;
import com.htw.kbe.maumau.ui.CardValue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceImplTest {

    private IPlayerService playerService;


    @BeforeEach
    public void setUp() {
        this.playerService = new PlayerServiceImpl();
    }

    @Test
    @DisplayName("Tets if List of Players is created with same size as names list")
    void createPlayersTestSize() {
        List<String> names = Arrays.asList("Thomas", "Max", "Simon");
        List<Player> createdPlayers = playerService.createPlayers(names);
        assertEquals(true, createdPlayers.size() == names.size());
    }


    @Test
    @DisplayName("Tets if List of Players is created contains the same names as name list")
    void createPlayersTestEntryNames() {
        boolean containsAllNames = true;
        List<String> names = Arrays.asList("Thomas", "Max", "Simon");
        List<Player> createdPlayers = playerService.createPlayers(names);
        int numAddedNames = names.size();

        for(Player player : createdPlayers) {
            if(names.contains(player.getUsername())){
                numAddedNames--;
            }
        }
        assertEquals(0, numAddedNames);
    }

    @Test
    @DisplayName("")
    void drawCards() {
        Player testPlayer = new Player("TestPlayer");
        Card testCard = new Card(CardColor.Heart, CardValue.Ace);
        playerService.drawCards(testPlayer, testCard);
        List<Card> updatedHandCards = testPlayer.getHandCards();
        assertEquals(true, updatedHandCards.contains(testCard));
    }

    @Test
    @DisplayName("")
    void playCard() {
    }
}