package com.htw.kbe.stack;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.stack.service.export.IStackService;
import com.htw.kbe.stack.service.export.Stack;
import com.htw.kbe.stack.service.service.StackServiceImpl;
import com.htw.kbe.stack.setup.StackSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StackServiceImplTest {

    private IStackService stackService;

    private ICardService cardService;

    private List<Card> gameCards;
    @InjectMocks
    private StackServiceImpl underTest;

    @BeforeEach
    public void setUp() {
        this.stackService = new StackServiceImpl();
//        this.gameCards = Stack
    }

    @Test
    @DisplayName("Tests if creaated stack has correct card amount of 32")
    void createdStackCardAmount() {
        Stack createdCards = stackService.createCardStack();
        assertEquals(createdCards.getDrawPile().size(), 32);
    }

    @Test
    @DisplayName("Tests if card stack is created correctly")
    void checksForAllCardst() {
        boolean containsAllCards = true;
        List<Card> createdCards = stackService.createCardStack().getDrawPile();
        List<Card> testStack = StackSetup.createStack().getDrawPile();

        for (Card card : testStack){
            if(!createdCards.contains(card)){
                containsAllCards = false;
            }
        }
        assertEquals(true, containsAllCards);
    }


    @Test
    @DisplayName("Tests if cards are shuffled")
    void shuffleCards() {
        List<Card> cardsToShuffle = new ArrayList<>();
        Card clubAce = new Card(CardColor.Club, CardValue.Ace);
        Card diamondKing = new Card(CardColor.Diamond, CardValue.King);
        Card heartKing = new Card(CardColor.Heart, CardValue.King);
        cardsToShuffle.add(clubAce);
        cardsToShuffle.add(diamondKing);
        cardsToShuffle.add(heartKing);
        stackService.shuffleCards(cardsToShuffle);
    }

    @Test
    @DisplayName("Checks if amount stack size reduces by draw amount")
    void drawCardsCorrectAmount() {
        Stack stack = StackSetup.createStack();
        List<Card> cardsToDraw = stackService.drawCards(stack, 2);
        assertEquals(3, cardsToDraw.size());
    }

    @Test
    @DisplayName("Checks if amount stack size reduces by draw amount")
    void drawCards() {
        Stack stack = StackSetup.createStack();
        int sizeDrawStack = stack.getDrawPile().size();
        List<Card> cardsToDraw = stackService.drawCards(stack, 2);
        assertEquals(2, cardsToDraw.size());
    }

    @Test
    @DisplayName("")
    void addCardToPlayedPile() {
    }

    @Test
    @DisplayName("")
    void setUpcard() {
    }

    @Test
    @DisplayName("")
    void testSetUpcard() {
    }

    @Test
    @DisplayName("")
    void redoDrawPile() {
    }
}