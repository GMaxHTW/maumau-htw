package com.htw.kbe.app;

import com.htw.kbe.card.export.ICardService;
import com.htw.kbe.card.service.CardServiceImpl;
import com.htw.kbe.controller.export.IGameController;
import com.htw.kbe.controller.service.GameController;
import com.htw.kbe.game.export.IGameService;
import com.htw.kbe.game.service.GameServiceImpl;
import com.htw.kbe.player.export.IPlayerService;
import com.htw.kbe.player.service.PlayerServiceImpl;
import com.htw.kbe.rule.export.IRulesService;
import com.htw.kbe.rule.service.RulesServiceImpl;
import com.htw.kbe.stack.export.IStackService;
import com.htw.kbe.stack.service.StackServiceImpl;
import com.htw.kbe.ui.export.IInputService;
import com.htw.kbe.ui.export.IUiService;
import com.htw.kbe.ui.service.InputServiceImpl;
import com.htw.kbe.ui.service.UiServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@ComponentScan(basePackages = {"com.htw.kbe.card", "com.htw.kbe.stack",
        "com.htw.kbe.controller", "com.htw.kbe.game", "com.htw.kbe.app",
        "com.htw.kbe.player", "com.htw.kbe.rule.service", "com.htw.kbe.ui"})
@Configuration
public class GameConfig {

    @Bean
    public ICardService cardService(){
        return new CardServiceImpl();
    }

    @Bean
    public IStackService stackService(){
        return new StackServiceImpl(cardService());
    }

    @Bean
    public  IRulesService rulesService(){
        return new RulesServiceImpl();
    }

    @Bean
    public IPlayerService playerService(){
        return new PlayerServiceImpl();
    }


    @Bean
    public IInputService inputService(){
        return new InputServiceImpl();
    }



    @Bean
    public IUiService uiService() {
        return new UiServiceImpl(cardService(),inputService());
    }

    @Bean
    public IGameService gameService(){
        return new GameServiceImpl(stackService(), rulesService(), playerService());
    }

    @Bean
    public IGameController gameController() {
        return new GameController(gameService(), uiService() );
    }

}
