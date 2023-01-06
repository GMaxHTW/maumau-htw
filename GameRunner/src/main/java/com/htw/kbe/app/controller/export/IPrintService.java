package com.htw.kbe.app.controller.export;

public interface IPrintService {

    /**
     * Prints the welcome message of the game
     */
    void printWelcomeMessage();

    /**
     * Get the number of players wanted for the game
     * @return
     */
    int getNumberOfPlayers();
}
