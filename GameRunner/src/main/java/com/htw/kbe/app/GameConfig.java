package com.htw.kbe.app;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.htw.kbe.card.card", "com.htw.kbe.controller", "com.htw.kbe.card.stack", "com.htw.kbe.app"})
public class GameConfig {
}
