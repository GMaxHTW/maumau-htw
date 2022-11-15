package com.htw.kbe.maumau.services;

import com.htw.kbe.maumau.interfaces.IRulesService;
import com.htw.kbe.maumau.model.Card;
import com.htw.kbe.maumau.model.Player;

public class RulesServiceImpl implements IRulesService{
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
