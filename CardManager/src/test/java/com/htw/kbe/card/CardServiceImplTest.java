package com.htw.kbe.card;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.card.service.CardServiceImpl;
import com.htw.kbe.card.setup.CardSetup;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {


    private ICardService cardService;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    public void setUp() {
        cardService = new CardServiceImpl();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Tests Method getColors
     */
    @Test
    @DisplayName("Tests if getColors Method returns 4 different colors")
    void getColorsTestSize() {
        int expectedSize = 4;
        List<CardColor> colors = cardService.getColors();
        assertEquals(expectedSize, colors.size());
    }

    /**
     * Tests Method getColors
     */
    @Test
    @DisplayName("Tests if getColors Method returns 4 different colors")
    void getColorsTestColorValues() {
        boolean containsAllCardColors = true;
        List<CardColor> expectedColorValues = CardSetup.cardColorList();
        List<CardColor> colors = cardService.getColors();

        for(CardColor color : colors) {
            if(!expectedColorValues.contains(color)){
                containsAllCardColors = false;
            }
        }
        assertTrue(containsAllCardColors);
    }

    @Test
    @DisplayName("Returns list of all card-values")
    void getValuesTestSize() {
        int expectedSize = 8;
        List<CardValue> colors = cardService.getValues();
        assertEquals(expectedSize, colors.size());
    }

    @Test
    @DisplayName("Returns list of all card-values")
    void getValuesTestValues() {
        boolean containsAllCardColors = true;
        List<CardValue> expectedColorValues = CardSetup.cardValueList();

        List<CardValue> values = cardService.getValues();

        for(CardValue value : values) {
            if(!expectedColorValues.contains(value)){
                containsAllCardColors = false;
            }
        }
        assertEquals(true, containsAllCardColors);
    }

    @Test
    @DisplayName("Test if there created card stack has correct size of 32")
    void getCardsTestSize() {
        int expectedSize = 32;
        List<Card> createdCards = cardService.createCards();
        assertEquals(expectedSize, createdCards.size());
    }

    @Test
    @DisplayName("Test if there created card stack has correct size of 32")
    void getCardsTestIfComplete() {
        boolean containsAllCards = true;
        List<Card> completeStack = CardSetup.gameCards();
        List<Card> createdCards = cardService.createCards();
        for (Card card: createdCards) {
            if (!completeStack.contains(card)) {
                containsAllCards = false;
            }
        }
        assertEquals(true, containsAllCards);
    }


    @Test
    @DisplayName("Checks if there are no cards double within the created Stack")
    void getCardsTestNoDoubles() {
        List<Card> createdCards = cardService.createCards();
        HashSet<Card> cardHashSet = new HashSet<>();

        for(Card card : createdCards) {
            cardHashSet.add(card);
        }
        assertEquals(createdCards.size(), cardHashSet.size());

    }


    @Test
    @DisplayName("Test if placed card matches with top card")
    void checkMatchingCards(){
        Card upperCard = new Card(CardColor.CLUB, CardValue.ACE);
        Card playedCard = new Card(CardColor.CLUB, CardValue.TEN);
        assertTrue(cardService.cardMatches(upperCard, playedCard));
    }

    @Test
    @DisplayName("Test if card is printed ")
    void checkPrintedCard(){
        Card card = new Card(CardColor.CLUB, CardValue.ACE);
        cardService.printCard(card);

        String toTestedCard = "┌─────────┐\n" +
                "│ " + CardValue.ACE.toString() + "" + "    │\n" +
                "│         │\n" +
                "│    "+ CardColor.CLUB.toString() + " │\n" +
                "│         │\n" +
                "│         │\n" +
                "└─────────┘\n";

        assertEquals(toTestedCard.trim(), outContent.toString().trim());
    }

    @Test
    @DisplayName("Test if upper card replaced with played card is printed ")
    void checkPrintedCardPlacing(){
        Card fromCard = new Card(CardColor.CLUB, CardValue.ACE);
        Card toCard = new Card(CardColor.CLUB, CardValue.TEN);
        cardService.printCardPlacing(fromCard, toCard);


        String toTestedOutput = "┌─────────┐"                                + "     " + "┌─────────┐\n" +
                "│ " + fromCard.getValue().toString() + "" + "      │" + "     " + "│ " + toCard.getValue().toString() + "" + "      │\n" +
                "│         │"                                + "     " + "│         │\n" +
                "│      " + fromCard.getColor().toString() + "│"            + " --> " + "  │     " + toCard.getColor().toString() + "│\n" +
                "│         │"                                + "     " + "│         │\n" +
                "│         │" + "     " + "│         │\n" +
                "└─────────┘"                                + "     " + "└─────────┘\n";

        assertEquals(toTestedOutput.trim(), outContent.toString().trim());
    }


    @Test
    @DisplayName("Test if hidden card is printed")
    void getHiddenCard(){

        cardService.printHiddenCard();

        String hiddenKart = "┌─────────┐\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "│░░░░░░░░░│\n"
                + "└─────────┘\n";

        assertEquals(hiddenKart.trim(), outContent.toString().trim());
    }

    @Test
    @DisplayName("Test if hand card of player is printed")
    void printCardList(){

        Collection<Card> cards = new HashSet<Card>();
        Card handCardOne = new Card(CardColor.CLUB, CardValue.TEN);
        Card handCardTwo = new Card(CardColor.HEART, CardValue.JACK);
        Card handCardThree = new Card(CardColor.DIAMOND, CardValue.KING);
        Card handCardFour = new Card(CardColor.SPADE, CardValue.NINE);
        cards.add(handCardOne);
        cards.add(handCardTwo);
        cards.add(handCardThree);
        cards.add(handCardFour);
        cardService.printCardList(cards);

        String handCard = "┌─────────┐ ┌─────────┐ ┌─────────┐ ┌─────────┐ \n" +
                "│    "+CardValue.NINE+"│ │    "+CardValue.JACK+"│ │    "+CardValue.TEN+"│ │    "+CardValue.KING+"│ \n" +
                "│         │ │         │ │         │ │         │ \n" +
                "│  "+CardColor.SPADE+"   │ │  "+CardColor.HEART+"   │ │  "+CardColor.CLUB+"   │ │  "+CardColor.DIAMOND+"   │ \n" +
                "│         │ │         │ │         │ │         │ \n" +
                "│         │ │         │ │         │ │         │ \n" +
                "└─────────┘ └─────────┘ └─────────┘ └─────────┘ \n" +
                "─ 1───────┘─ 2───────┘─ 3───────┘─ 4───────┘";

        assertEquals(handCard.trim(), outContent.toString().trim());

    }

}