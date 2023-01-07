package com.htw.kbe.controller.export;

import com.htw.kbe.game.exceptions.PlayerSizeInvalidException;
import com.htw.kbe.game.export.Game;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

public interface IGameController {

    /**
     * Starts the MauMau App
     */
    void startApp() throws PlayerSizeInvalidException;


    public Game initializeGame () throws PlayerSizeInvalidException;
}
