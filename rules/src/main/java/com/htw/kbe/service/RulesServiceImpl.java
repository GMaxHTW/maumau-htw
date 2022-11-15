package com.htw.kbe.service;

import com.htw.kbe.export.Card;
import com.htw.kbe.export.IRulesService;
import export.Player;

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
