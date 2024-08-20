package ar.edu.utn.frc.tup.lciii.model.deeds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RailwayTest {
    Railway railway;
    @BeforeEach
    public void setUP(){
        railway = new Railway(1, "", 1, 0, true, true, 1, "",500,1000,2000,4000,DeedType.RAILWAY);
    }

    @Test
    void getRent() {
        int quantity = 2;
        int result = railway.getRent(quantity);
        assertEquals(result,1000);
    }
    @Test
    void getRentNotEquals() {
        int quantity = 4;
        int result = railway.getRent(quantity);
        assertNotEquals(result,1000);
    }
}