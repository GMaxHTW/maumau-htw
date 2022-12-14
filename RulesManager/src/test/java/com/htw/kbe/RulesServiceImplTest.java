package com.htw.kbe;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.rule.exceptions.InvalidCardPlayedException;
import com.htw.kbe.rule.export.IRulesService;
import com.htw.kbe.rule.service.RulesServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


class RulesServiceImplTest {

    // Special cards MauMau
    // Seven    draw cards
    // Nine     change game direction
    // Jack     can play any card
    // Ace      sit out one round

    // Cards with special rules
    private final Card heartSeven = new Card(CardColor.HEART, CardValue.SEVEN);
    private final Card heartNine = new Card(CardColor.HEART, CardValue.NINE);
    private final Card heartJack = new Card(CardColor.HEART, CardValue.JACK);
    private final Card heartAce = new Card(CardColor.HEART, CardValue.ACE);


    // cards without special rules
    private final Card heartKing = new Card(CardColor.HEART, CardValue.KING);
    private final Card diamondKing = new Card(CardColor.DIAMOND, CardValue.KING);
    private final Card clubKing = new Card(CardColor.CLUB, CardValue.KING);
    private final Card heartEight = new Card(CardColor.HEART, CardValue.EIGHT);
    private final Card diamondEight = new Card(CardColor.DIAMOND, CardValue.EIGHT);
    private final Card clubEight = new Card(CardColor.CLUB, CardValue.EIGHT);


    // wished card
    private final CardColor colorWishClub = CardColor.CLUB;
    private final CardColor noWishedColor = null;


    private IRulesService rulesService;

    @BeforeEach
    public void setUp() {
        rulesService = new RulesServiceImpl();
    }

    // Card is valid when same CardValue or same CardColor
    @Test
    @DisplayName("Should not throw an exception when same color")
    void validatePlayerCardSameColor() throws InvalidCardPlayedException {
        rulesService.validatePlayerCard(heartKing, heartEight, null);
    }

    @Test
    @DisplayName("Should not throw an exception because same value")
    void validatePlayerCardSameValue() throws InvalidCardPlayedException {
        rulesService.validatePlayerCard(heartKing, diamondKing, null);
    }

    @Test
    @DisplayName("Checks if no valid color when color different from wishedColor")
    void validatePlayerCardDifferentColorFromWishedCard() {
        rulesService.validatePlayerCard(heartKing, diamondKing, CardColor.CLUB);
    }


    @Test
    @DisplayName("Return true if Card is Jack")
    void canPlayAnyCardValid() {
        boolean canPlayAnyCard = rulesService.canPlayAnyCard(heartJack);
        assertEquals(true, canPlayAnyCard);
    }

    @Test
    @DisplayName("Return false if Card is no Jack")
    void canPlayAnyCardInValid() {
        boolean canPlayAnyCard = rulesService.canPlayAnyCard(heartAce);
        assertEquals(false, canPlayAnyCard);
    }

    @Test
    @DisplayName("Return true if Card is Seven")
    void mustDrawCardsValid() {
        boolean mustDrawCard = rulesService.hasSeven(heartSeven);
        assertEquals(true, mustDrawCard);
    }

    @Test
    @DisplayName("Return false if Card is no Seven")
    void mustDrawCardsInValid() {
        boolean mustDrawCard = rulesService.hasSeven(heartJack);
        assertEquals(false, mustDrawCard);
    }

    @Test
    @DisplayName("Return true if Card is Ace")
    void mustSitOneOutValid() {
        boolean mustSitOut = rulesService.mustSitOneOut(heartAce);
        assertEquals(true, mustSitOut);
    }

    @Test
    @DisplayName("Return false if Card is no Ace")
    void mustSitOneOutInValid() {
        boolean mustSitOut = rulesService.mustSitOneOut(heartJack);
        assertEquals(false, mustSitOut);
    }

    @Test
    @DisplayName("Return true if Card is Nine")
    void validateGameDirectionValid() {
        boolean changeGameDirection = rulesService.changeGameDirection(heartNine);
        assertEquals(true, changeGameDirection);
    }

    @Test
    @DisplayName("Return false if Card is no Nine")
    void validateGameDirectionInValid() {
        boolean changeGameDirection = rulesService.changeGameDirection(heartJack);
        assertEquals(false, changeGameDirection);
    }

    @Test
    @DisplayName("Return true if Card is Seven")
    void validateMauValid() {
        Player playerValidMau = new Player("Max");
        List<Card> validMauCards = new ArrayList<>();
        validMauCards.add(heartAce);
        playerValidMau.setHandCards(validMauCards);
        boolean validMau = rulesService.validateMau(playerValidMau);
        assertEquals(true, validMau);

    }

    @Test
    @DisplayName("Return false if Card is no Seven")
    void validateMauInValid() {
        Player playerInValidMau = new Player("Khalil");
        List<Card> inValidMauCards = new ArrayList<>();
        inValidMauCards.add(heartAce);
        inValidMauCards.add(heartNine);
        playerInValidMau.setHandCards(inValidMauCards);
        rulesService.validateMau(playerInValidMau);
        boolean validMau = rulesService.validateMau(playerInValidMau);
        assertEquals(false, validMau);
    }

    @Test
    @DisplayName("Test if played card is matching with top card")
    void checkTopPlayedCard(){
        Card topCard = new Card(CardColor.CLUB, CardValue.KING);
        Card playedCard = new Card(CardColor.HEART, CardValue.KING);
        assertTrue(rulesService.checkLabelOrSuit(topCard, playedCard));
    }

    @Test
    @DisplayName("Test if there is already a jack played")
    void checkPlayedJack(){
        Card topCard = new Card(CardColor.CLUB, CardValue.JACK);
        Card playedCard = new Card(CardColor.HEART, CardValue.JACK);
        assertTrue(rulesService.jackOnJack(topCard, playedCard));
    }

    @Test
    @DisplayName("Test if the player has a 7 on his hand")
    void playerDraw(){
        Player player = new Player("Manu");
        List<Card> createdHandCards = new ArrayList<>();
        Card clubAce = new Card(CardColor.CLUB, CardValue.SEVEN);
        Card diamondKing = new Card(CardColor.DIAMOND, CardValue.KING);
        Card heartKing = new Card(CardColor.HEART, CardValue.KING);
        createdHandCards.add(clubAce);
        createdHandCards.add(diamondKing);
        createdHandCards.add(heartKing);

        player.setHandCards(createdHandCards);
        Card topCard = new Card(CardColor.SPADE, CardValue.SEVEN);
        assertTrue(rulesService.mustDraw(player, topCard));
    }

    @Test
    @DisplayName("Test if top Card is Eight an player has to suspend")
    void playerSuspend(){
        Card topCard = new Card(CardColor.CLUB, CardValue.EIGHT);
        assertTrue(rulesService.mustSuspend(topCard));
    }

    @Test
    @DisplayName("Test if played card is not a seven")
    void playedSeven(){
        Card topCard = new Card(CardColor.CLUB, CardValue.SEVEN);
        Card playedCard = new Card(CardColor.HEART, CardValue.ACE);
        assertTrue(rulesService.checkIfSeven(topCard, playedCard));
    }

    @Test
    @DisplayName("Test if amount of drawing Cards increase by sevens")
    void sevens(){
        Card topCard = new Card(CardColor.CLUB, CardValue.SEVEN);
        Card playedCard = new Card(CardColor.HEART, CardValue.SEVEN);
        int drawnCount = 0;
        if(!rulesService.checkIfSeven(topCard, playedCard)){
            int toDrawnCards = rulesService.NumberOfDrawnCardsBySeven();
            drawnCount = drawnCount + toDrawnCards;
        }
        assertEquals(2, drawnCount);
    }

}