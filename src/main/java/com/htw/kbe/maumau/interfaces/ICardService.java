package com.htw.kbe.maumau.interfaces;

import com.htw.kbe.maumau.model.Card;
import com.htw.kbe.maumau.ui.CardColor;
import com.htw.kbe.maumau.ui.CardValue;

import java.util.List;

public interface ICardService {

    public List<CardColor> getColors();
    public List<CardValue> getValues();
    public List<Card> getCards();
}
