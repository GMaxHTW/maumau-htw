package com.htw.kbe.stack.setup;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import com.htw.kbe.card.export.CardValue;
import com.htw.kbe.stack.export.Stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StackSetup {

    public static Stack createStack() {
        Stack stack = new Stack(createDrawStack());
        return stack;
    }



    public static List<Card> createDrawStack() {
        List<Card> gameCards = new ArrayList<>();
        for(CardColor color : CardColor.values()) {
            for(CardValue value : CardValue.values()) {
                gameCards.add(new Card(color, value));
            }
        }
        Collections.shuffle(gameCards);
        return gameCards;
    }


}
