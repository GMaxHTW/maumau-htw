package com.htw.kbe.rules.service.service;


import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.player.Player;
import com.htw.kbe.rules.service.exceptions.InvalidCardPlayedException;
import com.htw.kbe.rules.service.export.IRulesService;
import org.springframework.stereotype.Service;

@Service
public class RulesServiceImpl implements IRulesService {

    @Override
    public boolean validatePlayerCard(Card playerCard, Card currentUpcard, CardColor wishedColor) throws InvalidCardPlayedException {
        if (wishedColor != null) {
            if (playerCard.getColor() == wishedColor) {
                return true;
            } else {
                throw new InvalidCardPlayedException();
            }
        }

        if(playerCard.getValue() == currentUpcard.getValue() || playerCard.getColor() == currentUpcard.getColor()) {
            return true;
        }

        return false;
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
    public boolean validateMau(Player player) {
        return player.getHandCards().size() == 1;
    }


    @Override
    public int NumberOfDrawnCardsBySeven(){
        return 2;
    }
}
