package ar.edu.utn.frc.tup.lciii.model.config;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColorTest {
    @Test
    void enumValues(){
        Color[] expectedValues = {Color.RED , Color.GREEN , Color.BLUE,Color.YELLOW , Color.ORANGE , Color.VIOLET ,
                Color.BLACK , Color.WHITE};

        assertArrayEquals(Color.values(),expectedValues);
    }

    @Test
    void valueOf() {
        assertEquals(Color.GREEN, Color.valueOf("GREEN"));
        assertEquals(Color.BLACK, Color.valueOf("BLACK"));
    }
}