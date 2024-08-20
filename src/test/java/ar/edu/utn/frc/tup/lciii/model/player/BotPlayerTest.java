package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.model.config.Color;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BotPlayerTest {
    BotPlayer bot = new ConcreteBotPlayer(1, null, Color.BLACK, 1, 1, new ArrayList<>(), true,1, false, 0, false);
    @Test
    void buyProperty() {
    }


    @Test
    void addRanch() {
    }

}