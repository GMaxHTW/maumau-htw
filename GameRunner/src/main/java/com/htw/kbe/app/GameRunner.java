package com.htw.kbe.app;


import com.htw.kbe.controller.service.GameController;

public class GameRunner {

    public static void main(String[] args) {
        // Controller starts the game
        GameController gameController = new GameController();

        gameController.startApp();


    }
}
