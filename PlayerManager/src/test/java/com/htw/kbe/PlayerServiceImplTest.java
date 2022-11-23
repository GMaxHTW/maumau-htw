package com.htw.kbe;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.player.IPlayerService;
import com.htw.kbe.player.Player;
import com.htw.kbe.player.PlayerServiceImpl;
import com.htw.kbe.setup.PlayerSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlayerServiceImplTest {

    private IPlayerService playerService;
    Player testPlayer;





    @BeforeEach
    public void setUp() {
        this.playerService = new PlayerServiceImpl();
        this.testPlayer = PlayerSetup.createPlayer();
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
        Player testPlayer = PlayerSetup.createPlayer();
        Card validCard = new Card(CardColor.Club, CardValue.Ace);

        playerService.playCard(testPlayer, validCard);
        List<Card> updatedHandCards = testPlayer.getHandCards();
        assertEquals(false, updatedHandCards.contains(validCard));
    }
}