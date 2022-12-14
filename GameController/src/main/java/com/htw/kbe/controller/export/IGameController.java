package com.htw.kbe.controller.export;

import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import com.htw.kbe.game.export.IGameService;
import com.htw.kbe.ui.export.IUiService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

public interface IGameController {

    /**
     * Starts the MauMau App
     */
    void startApp() throws PlayerSizeInvalidException;


    void startGame(IGameService gameService, IUiService uiService, Game game);

    void handlePlayerChoice(Game game);
    Game initializeGame () throws PlayerSizeInvalidException;
}
