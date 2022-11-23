<<<<<<< HEAD:CardManager/src/main/java/com/htw/kbe/card/export/Card.java
package com.htw.kbe.card.export;

=======
package com.htw.kbe.maumau.model;

import com.htw.kbe.maumau.model.enums.CardColor;
import com.htw.kbe.maumau.model.enums.CardValue;
>>>>>>> f34598a66161a404380596af167c9249b1be9936:src/main/java/com/htw/kbe/maumau/model/Card.java

public class Card {

    private CardColor color;
    private CardValue value;

    public Card(CardColor color, CardValue value) {
        this.color = color;
        this.value = value;
    }

    public Card(){}

    public CardColor getColor() {
        return color;
    }

    public void setColor(CardColor color) {
        this.color = color;
    }

    public CardValue getValue() {
        return value;
    }

    public void setValue(CardValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "color=" + color +
                ", value=" + value +
                '}';
    }
}
