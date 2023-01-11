package com.htw.kbe.ui.export;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;

import java.util.List;

public interface IInputService {
    public int getNumberOfPlayers();
    public List<String> getPlayerNames (int playersTotal);
    public String getNameOfPlayer();
    public CardColor wishColor();

    public Card selectCardToPlay(List<Card> activeHandCards, Card currentUpCard);
}
