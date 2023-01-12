package com.htw.kbe.ui.export;

import com.htw.kbe.card.export.Card;
import com.htw.kbe.card.export.CardColor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IInputService {
    public int getNumberOfPlayers();
    public List<String> getPlayerNames (int playersTotal);
    public String getNameOfPlayer();
    public CardColor wishColor();

    public boolean saidMau();


    public Card selectCardToPlay(List<Card> activeHandCards, Card currentUpCard);
}
