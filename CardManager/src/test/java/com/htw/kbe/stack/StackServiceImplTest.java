package com.htw.kbe.stack;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.card.service.CardServiceImpl;
import com.htw.kbe.card.setup.CardSetup;
import com.htw.kbe.stack.export.IStackService;
import com.htw.kbe.stack.export.Stack;
import com.htw.kbe.stack.service.StackServiceImpl;
import com.htw.kbe.stack.setup.StackSetup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StackServiceImplTest {

    private IStackService stackService;

    private CardServiceImpl cardService;

    private List<Card> gameCards;
    @InjectMocks
    private StackServiceImpl underTest;

    @BeforeEach
    public void setUp() {
        this.cardService = new CardServiceImpl();
        this.stackService = new StackServiceImpl(cardService);
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
    void checksForAllCards() {
        boolean containsAllCards = true;
        List<Card> createdCards = stackService.createCardStack().getDrawPile();
        List<Card> testStack = CardSetup.gameCards();

        for (Card card : testStack){
            // TODO: Why is this not working?
            if(!createdCards.contains(card)){
                containsAllCards = false;
            }
        }
        assertEquals(true, containsAllCards);
    }


    @Test
    @DisplayName("Test Checks if the first two cards in the list are different after shuffling the cards")
    void shuffleCardsFirstTwoCardsDifferent() {
        boolean cardsAreDifferent = true;

        List<Card> cardsToShuffle = CardSetup.cardListTesting();
        Card firstCardBeforeShuffle = cardsToShuffle.get(0);
        Card secondCardBeforeShuffle = cardsToShuffle.get(1);
        stackService.shuffleCards(cardsToShuffle);
        Card firstCardAfterShuffle = cardsToShuffle.get(0);
        Card secondCardAfterShuffle = cardsToShuffle.get(1);

        assertEquals(false, firstCardBeforeShuffle.equals(firstCardAfterShuffle));
        assertEquals(false, secondCardBeforeShuffle.equals(secondCardAfterShuffle));

    }


    @Test
    @DisplayName("Checks if the index of cards is changing")
    void shuffleCardsCheckCardIndexes() {
        boolean indexIsDifferent = true;

        List<Card> cardsToShuffle = CardSetup.cardListTesting();
        Card firstCardBeforeShuffle = cardsToShuffle.get(0);
        Card secondCardBeforeShuffle = cardsToShuffle.get(1);
        stackService.shuffleCards(cardsToShuffle);
        int indexFirstCard = cardsToShuffle.indexOf(firstCardBeforeShuffle);
        int indexSecondCard = cardsToShuffle.indexOf(secondCardBeforeShuffle);

        if(indexFirstCard == 0 && indexSecondCard == 1) {
            indexIsDifferent = false;
        }

        assertEquals(true, indexIsDifferent);
    }

    @Test
    @DisplayName("Checks drawCards return CardList with 2 cards")
    void drawCardsCorrectAmount() {
        Stack stack = StackSetup.createStack();
        List<Card> cardsToDraw = stackService.drawCards(stack, 2);
        assertEquals(2, cardsToDraw.size());
    }

    @Test
    @DisplayName("Checks if size of drawPile contains 2 cards less")
    void drawCardsAdaptedStackSize() {
        Stack stack = StackSetup.createStack();
        int sizeBeforeDrawing = stack.getDrawPile().size();
        List<Card> cardsToDraw = stackService.drawCards(stack, 2);
        int sizeAfterDrawing = stack.getDrawPile().size();
        assertEquals(sizeBeforeDrawing, sizeAfterDrawing+2);
    }



    @Test
    @DisplayName("Checks if Card heartKing is added in the first position of the playedCardsPile")
    void addCardToPlayedPileCheckFirstCard() {
        Stack stack = StackSetup.createStack();
        Card heartKing = CardSetup.getHeartKing();
        stackService.addCardToPlayedPile(stack, heartKing);
        List<Card> playedCards = stack.getPlayedCards();
        Card firstCard = playedCards.get(0);
        assertEquals(firstCard, heartKing);
    }

    @Test
    @DisplayName("Checks if the size of the played pile increased by one")
    void addCardToPlayedPileCheckSize() {
        Stack stack = StackSetup.createStack();
        int sizeBeforeAdding = stack.getPlayedCards().size();
        Card heartKing = CardSetup.getHeartKing();
        stackService.addCardToPlayedPile(stack, heartKing);
        int sizeAfterAdding = stack.getPlayedCards().size();
        assertEquals(sizeBeforeAdding, sizeAfterAdding-1);
    }

    @Test
    @DisplayName("Tests if the first card of drawPile is added as upCard")
    void setFirstUpCardAddedAsUpCard() {
        Stack stack = StackSetup.createStack();
        Card firstCardDraw = stack.getDrawPile().get(0);
        stackService.setFirstUpCard(stack);
        Card upCard = stack.getUpCard();
        assertEquals(firstCardDraw, upCard);
    }

    @Test
    @DisplayName("Tests if the first card of drawPile is added to playedCards")
    void setFirstUpCardAddedToPlayedCards() {
        Stack stack = StackSetup.createStack();
        Card firstCardDraw = stack.getDrawPile().get(0);
        stackService.setFirstUpCard(stack);
        Card firstCardPlayedCards = stack.getPlayedCards().get(0);
        assertEquals(firstCardDraw, firstCardPlayedCards);
    }

    @Test
    @DisplayName("Tests new heartKind is added as new upCard  ")
    void testSetNewUpCardAddedAsNewUpcard() {
        Stack stack = StackSetup.createStack();
        Card heartKing = CardSetup.getHeartKing();
        stackService.setNewUpCard(stack, heartKing);
        assertEquals(heartKing, stack.getUpCard());
    }

    @Test
    @DisplayName("Tests new heartKind is added as first card in playedCards")
    void testSetNewUpCard() {
        Stack stack = StackSetup.createStack();
        Card heartKing = CardSetup.getHeartKing();
        stackService.setNewUpCard(stack, heartKing);
        assertEquals(heartKing, stack.getPlayedCards().get(0));
    }

    @Test
    @DisplayName("Test if get one card to draw")
    void testDrawSingleCard(){
        Stack stack = stackService.createCardStack();
        Card toDrawCard = stackService.drawCard(stack);

        assertNotNull(toDrawCard);
    }

    @Test
    @DisplayName("Test if get one card to draw and the stack amount changed")
    void testAmountStack(){
        Stack stack = stackService.createCardStack();
        assertEquals(stack.getDrawPile().size(), 32);
        Card toDrawCard = stackService.drawCard(stack);
        assertEquals(stack.getDrawPile().size(), 31);
    }

    @Test
    @DisplayName("Test if draw Pile get filled")
    void fillDrawPile(){
        Stack stack = stackService.createCardStack();
        List<Card> playedCards = stackService.drawCards(stack, 32);
        stack.setPlayedCards(playedCards);
        assertEquals(0, stack.getDrawPile().size());
        Card toDrawCard = stackService.drawCard(stack);
        assertEquals(stack.getDrawPile().size(), 31);
    }
}