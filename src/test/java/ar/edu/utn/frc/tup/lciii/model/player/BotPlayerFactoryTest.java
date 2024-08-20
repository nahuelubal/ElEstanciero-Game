package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.services.impl.BotPlayerFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotPlayerFactoryTest {

    private final BotPlayerFactoryImpl botFactory = new ConcreteBotPlayerFactory();

    @Test
    void testCreateBotAggresive() {
        AbstractPlayer bot  = botFactory.createBotPlayer(BotPlayerTypes.AGGRESSIVE);
        assertInstanceOf(AggressiveBotPlayer.class,bot);
    }
    @Test
    void testCreateBotWellBalanced() {
        AbstractPlayer bot  = botFactory.createBotPlayer(BotPlayerTypes.WELL_BALANCED);
        assertInstanceOf(WellBalancedBotPlayer.class,bot);
    }

    @Test
    void testCreateConservative() {
        AbstractPlayer bot  = botFactory.createBotPlayer(BotPlayerTypes.CONSERVATIVE);
        assertInstanceOf(ConservativeBotPlayer.class,bot);
    }
}