package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BotPlayerTypesTest {

    @Test
    void enumValues(){
        BotPlayerTypes[] expectedValues = {BotPlayerTypes.CONSERVATIVE,BotPlayerTypes.WELL_BALANCED,BotPlayerTypes.AGGRESSIVE};

        assertArrayEquals(BotPlayerTypes.values(),expectedValues );
    }

    @Test
    void valueOf() {
        assertEquals(BotPlayerTypes.AGGRESSIVE , BotPlayerTypes.valueOf("AGGRESSIVE"));
        assertEquals(BotPlayerTypes.WELL_BALANCED , BotPlayerTypes.valueOf("WELL_BALANCED"));
        assertEquals(BotPlayerTypes.CONSERVATIVE , BotPlayerTypes.valueOf("CONSERVATIVE"));
    }
}