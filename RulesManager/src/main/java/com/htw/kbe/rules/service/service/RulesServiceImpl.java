package com.htw.kbe.rules.service.service;


import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.player.Player;
import com.htw.kbe.rules.service.exceptions.InvalidCardPlayedException;
import com.htw.kbe.rules.service.export.IRulesService;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class RulesServiceImpl implements IRulesService {

    private static Logger logger = LogManager.getLogger(RulesServiceImpl.class);

    //ToDO: Kann raus
    @Override
    public boolean validatePlayerCard(Card playerCard, Card currentUpcard, CardColor wishedColor) throws InvalidCardPlayedException {

        if(playerCard.getValue() == currentUpcard.getValue() || playerCard.getColor() == currentUpcard.getColor()) {
            return true;
        }

        return false;
    }


    @Override
    public void validateCard(Card playerCard, Card currentUpcard, CardColor wishedColor) throws InvalidCardPlayedException {
        if(!checkLabelOrSuit(currentUpcard, playerCard)) {
            logger.info("The card {} is not valid because either the label or suit does not match", playerCard);
            throw new InvalidCardPlayedException("Card cannot be played because either the label or the suit does not match");
        }
        if(jackOnJack(currentUpcard, playerCard)){
            logger.info("The card {} is not valid because there is already a Jack on it", playerCard);
            throw new InvalidCardPlayedException("Card can not be played because there is already a jack. \"Jack on Jack Stinks\"");
        }
        if(checkIfSeven(currentUpcard, playerCard)){
            logger.info("The card {} is not valid because a 7 must be placed", playerCard);
            throw new InvalidCardPlayedException("Card cannot be played because there is already a 7");
        }
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
