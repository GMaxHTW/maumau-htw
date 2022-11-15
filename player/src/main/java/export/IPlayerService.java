package export;

import com.htw.kbe.export.Card;

import java.util.List;

public interface IPlayerService {

    /**
     * Creates the Human and the Computer Players
     * @param usernames Player names
     * @return      the list of Players currently playing
     */
    List<Player> createPlayers(List<String> usernames);
    /**
     * Adds a card to the Player's hand cards
     * @param player the active Player
     * @param card the card to be added to the player's hand cards
     */
    void drawCards(Player player, Card card);
    /**
     * Removes a card from the Player's hand cards
     * @param player the active Player
     * @param card the card to be removed from the player's hand cards
     */
    void playCard(Player player, Card card);

}