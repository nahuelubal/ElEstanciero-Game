package ar.edu.utn.frc.tup.lciii.others.console_writer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestConsoleConfig {

    @Test
    void getAlign() {
        ConsoleConfig config = new ConsoleConfig(Align.CENTER);
        assertEquals(Align.CENTER, config.getAlign());
        assertFalse(config.isBold());
        assertEquals(181, config.getSize());
    }

    @Test
    void isBold() {
        ConsoleConfig config = new ConsoleConfig();
        assertEquals(Align.LEFT, config.getAlign());
        assertFalse(config.isBold());
        assertEquals(181, config.getSize());
    }

    @Test
    void getDEFAULT_SIZE() {
        ConsoleConfig config = new ConsoleConfig();
        assertEquals(181, config.getDEFAULT_SIZE());
    }
}