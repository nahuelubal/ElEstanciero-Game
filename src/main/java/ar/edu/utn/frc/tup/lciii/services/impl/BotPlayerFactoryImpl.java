package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.model.player.*;
import ar.edu.utn.frc.tup.lciii.interfaces.player.IBotPlayerFactory;

public class BotPlayerFactoryImpl implements IBotPlayerFactory {
    public BotPlayer createBotPlayer(BotPlayerTypes botPlayerType){

        return switch (botPlayerType){
            case CONSERVATIVE -> new ConservativeBotPlayer();
            case WELL_BALANCED -> new WellBalancedBotPlayer();
            case AGGRESSIVE -> new AggressiveBotPlayer();
            default -> null;
        };


/*
    Random r = new Random();

        int a = r.nextInt(3);
        return switch (a){
            case 1 -> new ConservativeBotPlayer();
            case 2 -> new WellBalancedBotPlayer();
            case 3 -> new AggressiveBotPlayer();
            default -> null;
        };
        */
    }
}
