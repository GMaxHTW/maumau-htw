package com.htw.kbe.app;



import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.controller.service.GameController;
import com.htw.kbe.game.export.IGameService;
import com.htw.kbe.player.export.IPlayerService;
import com.htw.kbe.rule.export.IRulesService;
import com.htw.kbe.stack.export.IStackService;
import com.htw.kbe.ui.export.IInputService;
import com.htw.kbe.ui.export.IUiService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class GameRunner {


    public static void main(String[] args) {
        // Controller starts the game
//        GameController gameController = registerComponents();
//        gameController.startApp();

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GameConfig.class);
        IInputService inputService = context.getBean(IInputService.class);
        IPlayerService playerService = context.getBean(IPlayerService.class);
        ICardService cardService = context.getBean(ICardService.class);
        IStackService stackService = context.getBean(IStackService.class);
        IRulesService rulesService = context.getBean(IRulesService.class);
        IUiService uiService = context.getBean(IUiService.class);
        IGameService gameService = context.getBean(IGameService.class);
        GameController gameController = context.getBean(GameController.class);


        gameController.startApp();



    }



    // This is not needed
//    private static GameController registerComponents() {
//        // TODO: Make this clean using import via dependency injection
//        // instance the different dependencies
//        CardServiceImpl cardService = new CardServiceImpl();
//        PlayerServiceImpl playerService = new PlayerServiceImpl();
//        InputService inputService = new InputService();
//        StackServiceImpl stackService = new StackServiceImpl(cardService);
//        RulesServiceImpl rulesService = new RulesServiceImpl();
//        UiService uiService = new UiService(cardService, inputService);
//        GameServiceImpl gameService = new GameServiceImpl(stackService, cardService, rulesService, playerService);
//        GameController gameController = new GameController(gameService, uiService);
//        return gameController;
//    }

}
