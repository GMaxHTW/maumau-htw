package com.htw.kbe.rules.service.service;


import com.htw.kbe.card.export.Card;
import com.htw.kbe.player.Player;
import com.htw.kbe.rules.service.export.IRulesService;

public class RulesServiceImpl implements IRulesService {

    @Override
    public boolean validatePlayerCard(Card playerCard, Card currentUpcard) {
        return false;
    }

    @Override
    public boolean cardIsJack(Card card) {
        return false;
    }

    @Override
    public boolean mustDrawCards(Card card) {
        return false;
    }

    @Override
    public boolean mustSitOneOut(Card card) {
        return false;
    }

    @Override
    public boolean validateGameDirection(Card card) {
        return false;
    }

    @Override
    public boolean validateMau(Player player) {
        return false;
    }
}
