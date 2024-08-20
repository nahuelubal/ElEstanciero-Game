package ar.edu.utn.frc.tup.lciii.model.config;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiceTest {
    Dice dice;
    @BeforeEach
    void setUp(){
         dice = spy(Dice.class);
    }
    @Test
    void rollDices() {
        Dice dice = new Dice();
        Integer result = dice.rollDices();

        assertTrue(result >= 2 && result <= 12);
        assertTrue(dice.getDice1() >= 1 && dice.getDice1() <= 6);
        assertTrue(dice.getDice2() >= 1 && dice.getDice2() <= 6);

    }

    @Test
    void areDicesEqualsTest() {
        dice.setDice1(3);
        dice.setDice2(3);

        assertTrue(dice.areDicesEquals());
    }
    @Test
    public void areDicesNotEquals() {
        dice.setDice1(2);
        dice.setDice2(3);

        assertFalse(dice.areDicesEquals());
    }
    @Test
    void getDice1() {
        Dice dice = new Dice();
        dice.setDice1(4);
        assertEquals(4, dice.getDice1());
    }

    @Test
    void getDice2() {
        Dice dice = new Dice();
        dice.setDice2(5);
        assertEquals(5, dice.getDice2());
    }

    @Test
    void getAddition() {
        Dice dice = new Dice();
        dice.setAddition(9);
        assertEquals(9, dice.getAddition());
    }

    @Test
    void setDice1() {
        Dice dice = new Dice();
        dice.setDice1(3);
        assertEquals(3, dice.getDice1());
    }

    @Test
    void setDice2() {
        Dice dice = new Dice();
        dice.setDice2(6);
        assertEquals(6, dice.getDice2());
    }

    @Test
    void setAddition() {
        Dice dice = new Dice();
        dice.setAddition(10);
        assertEquals(10, dice.getAddition());
    }
}