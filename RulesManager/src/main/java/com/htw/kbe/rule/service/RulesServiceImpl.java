package com.htw.kbe.rule.service;


import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.player.export.Player;
import com.htw.kbe.rule.export.IRulesService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RulesServiceImpl implements IRulesService {

    private static Logger logger = LogManager.getLogger(RulesServiceImpl.class);


    @Override
    public boolean validatePlayerCard(Card playerCard, Card currentUpcard, CardColor wishedColor) {
        if(!checkLabelOrSuit(currentUpcard, playerCard)) {
            logger.info("The card {} is not valid because either the label or suit does not match", playerCard);
            return false;
        }
        if(jackOnJack(currentUpcard, playerCard)){
            logger.info("The card {} is not valid because there is already a Jack on it", playerCard);
            return false;
        }
        if(checkIfSeven(currentUpcard, playerCard)){
            logger.info("The card {} is not valid because a 7 must be placed", playerCard);
            return false;
        }
        if(wishedColor != null && playerCard.getColor() != wishedColor) {
            logger.info("The card {} cannot be played because it does not match the wished color {}", playerCard, wishedColor );
            return false;
        }
        return true;
    }

    @Override
    public boolean checkIfSeven(Card topCard, Card playedCard){
        return topCard.getValue().equals(CardValue.SEVEN) && !playedCard.getValue().equals(CardValue.SEVEN);
    }

    @Override
    public boolean checkLabelOrSuit(Card topCard, Card playedCard){
        return playedCard.getValue() == topCard.getValue() || playedCard.getColor() == topCard.getColor();
    }

    @Override
    public boolean canPlayAnyCard(Card card) {
        return card.getValue().equals(CardValue.JACK);
    }

    @Override
    public boolean jackOnJack(Card topCard, Card playedCard){
        return topCard.getValue().equals(CardValue.JACK) && playedCard.getValue().equals(CardValue.JACK);
    }


    // When card is seven
    @Override
    public boolean mustDrawCards(Card card) {
        return card.getValue().equals(CardValue.SEVEN);
    }

    @Override
    public boolean mustDrawCardsExtends(Player player, Card topCard){
        boolean topCardSeven = mustDrawCards(topCard);
        boolean playerHasSeven = player.getHandCards().stream().anyMatch(seven -> seven.getValue().equals(CardValue.SEVEN));
        return topCardSeven && playerHasSeven;

    }

    @Override
    public boolean mustSitOneOut(Card card) {
        return card.getValue().equals(CardValue.ACE);
    }

    @Override
    public boolean changeGameDirection(Card card) {
        return card.getValue().equals(CardValue.NINE);
    }


    @Override
    public boolean mustSuspend(Card card){
        return card.getValue().equals(CardValue.EIGHT);
    }

    @Override
    public boolean validateMau(Player player) {
        return player.getHandCards().size() == 1;
    }


    @Override
    public int NumberOfDrawnCardsBySeven(){
        return 2;
    }
}