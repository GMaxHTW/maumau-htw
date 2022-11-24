package com.htw.kbe.rules.service.service;


import com.htw.kbe.card.card.export.Card;
import com.htw.kbe.card.card.export.CardColor;
import com.htw.kbe.card.card.export.CardValue;
import com.htw.kbe.player.Player;
import com.htw.kbe.rules.service.exceptions.InvalidCardPlayedException;
import com.htw.kbe.rules.service.export.IRulesService;

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
        if(card.getValue().equals(CardValue.JACK)) {
            return true;
        } else {
            return false;
        }
    }

    // When card is seven
    @Override
    public boolean mustDrawCards(Card card) {
        if(card.getValue().equals(CardValue.SEVEN)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean mustSitOneOut(Card card) {
        if(card.getValue().equals(CardValue.ACE)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean changeGameDirection(Card card) {
        if(card.getValue().equals(CardValue.NINE)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean validateMau(Player player) {
        if(player.getHandCards().size() == 1) {
            return true;
        } else {
            return false;
        }
    }
}
