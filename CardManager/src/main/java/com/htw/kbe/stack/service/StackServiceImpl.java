package com.htw.kbe.stack.service;


import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.stack.export.IStackService;
import com.htw.kbe.stack.export.Stack;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StackServiceImpl implements IStackService {

    private ICardService cardService;

    @Autowired
    public StackServiceImpl(ICardService cardService) {
        this.cardService = cardService;
    }

    private static Logger logger = LogManager.getLogger(StackServiceImpl.class);



    @Override
    public Stack createCardStack() {
        Stack stack = new Stack();
        List<Card> createdDrawPile = cardService.createCards();
        stack.setDrawPile(createdDrawPile);
        logger.info("Stack got created: {}", stack);
        return stack;
    }

    @Override
    public void shuffleCards(List<Card> cards) {
        Collections.shuffle(cards);
        logger.info("Cards got shuffled");
    }

    @Override
    public List<Card> drawCards(Stack stack, int drawAmount) {
        List<Card> drawPile = stack.getDrawPile();
        List<Card> cardsToDraw = drawPile.subList(0, drawAmount);
        stack.getDrawPile().removeAll(cardsToDraw);
        logger.info("There were {} cards drawn from the stack", drawAmount);
        return cardsToDraw;
    }

    @Override
    public Card drawCard(Stack stack) {
        if(stack.getDrawPile().isEmpty()) {
            fillDrawPile(stack);
        }
        List<Card> drawPile = stack.getDrawPile();
        Card drawCard = drawPile.remove(0);
        return drawCard;
    }

    @Override
    public void fillDrawPile(Stack stack) {
        List<Card> playedCards = stack.getPlayedCards();
        shuffleCards(playedCards);
        stack.setDrawPile(playedCards);
        playedCards.removeAll(playedCards);
    }

    @Override
    public List<Card> addCardToPlayedPile(Stack stack, Card card) {
        List<Card> playedCards = stack.getPlayedCards();
        playedCards.add(card);
        logger.info("The {} card was added to the played pile", card);
        return playedCards;
    }



    @Override
    public Card setFirstUpCard(Stack stack) {
        Card upCard = stack.getDrawPile().get(0);
        stack.getPlayedCards().add(upCard);
        stack.setUpCard(upCard);
        logger.info("The first up card is {}", upCard);
        return upCard;
    }


    @Override
    public Card setNewUpCard(Stack stack, Card newCard) {
        stack.setUpCard(newCard);
        stack.getPlayedCards().add(newCard);
        logger.info("New up card is: {}", newCard);
        return newCard;
    }


}