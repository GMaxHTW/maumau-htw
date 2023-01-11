package com.htw.kbe;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.player.service.PlayerServiceImpl;
import com.htw.kbe.setup.PlayerSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

//    private IPlayerService playerService;
    Player testPlayer;
    private PlayerServiceImpl playerService;


    @BeforeEach
    public void setUp() {
        this.playerService = new PlayerServiceImpl();
        this.testPlayer = PlayerSetup.createPlayer();
    }

    @Test
    @DisplayName("Tets if list of players is created with same size as names list")
    void createPlayersTestSize() {
        List<String> names = Arrays.asList("Thomas", "Max", "Simon");
        List<Player> createdPlayers = playerService.createPlayers(names);
        assertEquals(true, createdPlayers.size() == names.size());
    }



    @Test
    @DisplayName("Tets if list of players is created contains the same names as name list")
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
    @DisplayName("Tets if testCard is added to the handCards of the player")
    void drawCard() {
        Player testPlayer = new Player("TestPlayer");
        Card testCard = new Card(CardColor.HEART, CardValue.ACE);
        playerService.drawCard(testPlayer, testCard);
        List<Card> updatedHandCards = testPlayer.getHandCards();
        assertEquals(true, updatedHandCards.contains(testCard));
    }

    @Test
    @DisplayName("Tests if playedCard is removed from hand of player")
    void playCard() {
        Player testPlayer = PlayerSetup.createPlayer();
        Card validCard = new Card(CardColor.CLUB, CardValue.ACE);

        playerService.playCard(testPlayer, validCard);
        List<Card> updatedHandCards = testPlayer.getHandCards();
        assertEquals(false, updatedHandCards.contains(validCard));
    }
}