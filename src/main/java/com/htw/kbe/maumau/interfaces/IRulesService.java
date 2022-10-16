package com.htw.kbe.maumau.interfaces;

import com.htw.kbe.maumau.model.Card;

public interface IRulesService {
    /**
     * This is a Javadoc template
     * @param param param description
     * @return      return value description
     */

    public boolean validateCard(Card card);
    public boolean matchValueColor(Card newCard, Card upcard);
    public boolean cardIsJack(Card card);
    public boolean cardIsSeven(Card card);
    public boolean cardIsAce(Card card);
}
