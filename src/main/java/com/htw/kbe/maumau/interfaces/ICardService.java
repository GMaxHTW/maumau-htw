package com.htw.kbe.maumau.interfaces;

import com.htw.kbe.maumau.model.Card;
import com.htw.kbe.maumau.ui.CardColor;
import com.htw.kbe.maumau.ui.CardValue;

import java.util.List;

public interface ICardService {
    /**
     * Lists all possible card colors
     * @return      List with all Elements of CardColor
     */
    List<CardColor> getColors();
    /**
     * Lists all possible card values
     * @return      List with all Elements of CardValue
     */
    List<CardValue> getValues();
    /**
     * Creates all possible card combinations of CardColor and CardValue
     * @return      List of Cards in all possible combinations of Color and Value
     */
    List<Card> getCards();
}
