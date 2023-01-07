package com.htw.kbe.app;


import com.htw.kbe.controller.service.GameController;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class GameRunner {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new AnnotationConfigApplicationContext(GameConfig.class);

        // Controller starts the game
        final GameController gameController = applicationContext.getBean(GameController.class);
        gameController.startApp();

        applicationContext.close();


    }
}
