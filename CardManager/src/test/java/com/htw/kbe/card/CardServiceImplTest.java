package com.htw.kbe.card;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.card.service.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CardServiceImplTest {


    private ICardService cardService;
    @InjectMocks
    private CardServiceImpl underTest;


    @BeforeEach
    public void setUp() {
        cardService = new CardServiceImpl();
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
        List<CardColor> expectedColorValues = Arrays.asList(CardColor.Club, CardColor.Diamond, CardColor.Heart, CardColor.Spade);
        List<CardColor> colors = cardService.getColors();

        for(CardColor color : colors) {
            if(!expectedColorValues.contains(color)){
                containsAllCardColors = false;
            }
        }
        assertEquals(true, containsAllCardColors);
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
        List<CardValue> expectedColorValues = Arrays.asList(CardValue.Ace, CardValue.Seven,
                CardValue.Eight, CardValue.Nine, CardValue.Ten, CardValue.Jack, CardValue.Queen, CardValue.King);

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
    @DisplayName("Checks if there are no cards double within the created Stack")
    void getCardsTestNoDoubles() {
        List<Card> createdCards = cardService.createCards();
        HashSet<Card> cardHashSet = new HashSet<>();

        for(Card card : createdCards) {
            cardHashSet.add(card);
        }
        assertEquals(createdCards.size(), cardHashSet.size());

    }
}