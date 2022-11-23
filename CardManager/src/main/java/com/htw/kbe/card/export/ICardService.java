package com.htw.kbe.card.export;

<<<<<<< HEAD:CardManager/src/main/java/com/htw/kbe/card/export/ICardService.java
=======
import com.htw.kbe.maumau.model.Card;
import com.htw.kbe.maumau.model.enums.CardColor;
import com.htw.kbe.maumau.model.enums.CardValue;
>>>>>>> f34598a66161a404380596af167c9249b1be9936:src/main/java/com/htw/kbe/maumau/interfaces/ICardService.java

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
    List<Card> createCards();
}
