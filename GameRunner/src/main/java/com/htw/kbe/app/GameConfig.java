package com.htw.kbe.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan(basePackages = {"com.htw.kbe.card.card", "com.htw.kbe.controller",
        "com.htw.kbe.card.stack", "com.htw.kbe.app", "com.htw.kbe.controller",
         "com.htw.kbe.game", "com.htw.kbe.rule", "com.htw.kbe.ui"})
@Configuration
public class GameConfig {
}
