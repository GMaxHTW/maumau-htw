package com.htw.kbe.app;



import com.htw.kbe.card.service.CardServiceImpl;
import com.htw.kbe.controller.service.GameController;
import com.htw.kbe.game.service.GameServiceImpl;
import com.htw.kbe.player.service.PlayerServiceImpl;
import com.htw.kbe.rule.service.RulesServiceImpl;
import com.htw.kbe.stack.service.StackServiceImpl;
import com.htw.kbe.ui.service.InputService;
import com.htw.kbe.ui.service.UiService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class GameRunner {

    public static void main(String[] args) {
        // Controller starts the game
        GameController gameController = registerComponents();
        gameController.startApp();

    }



    // This is not needed
    private static GameController registerComponents() {
        // TODO: Make this clean using import via dependency injection
        // instance the different dependencies
        CardServiceImpl cardService = new CardServiceImpl();
        PlayerServiceImpl playerService = new PlayerServiceImpl();
        InputService inputService = new InputService();
        UiService uiService = new UiService(cardService, inputService);
        StackServiceImpl stackService = new StackServiceImpl(cardService);
        RulesServiceImpl rulesService = new RulesServiceImpl();

        // inject dependencies into service and controller
        GameServiceImpl gameService = new GameServiceImpl(stackService, cardService, rulesService, playerService);
        GameController gameController = new GameController(gameService, uiService);
        return gameController;
    }

}
