package ar.edu.utn.frc.tup.lciii.others.console_writer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAlign {

	@Test
	void values() {
		Align[] expected = {
				Align.LEFT,
				Align.CENTER,
				Align.RIGHT
		};
		assertArrayEquals(Align.values(),expected);
	}

	@Test
	void valueOf() {
		assertEquals(Align.LEFT, Align.valueOf("LEFT"));
		assertEquals(Align.CENTER, Align.valueOf("CENTER"));
		assertEquals(Align.RIGHT, Align.valueOf("RIGHT"));
	}
}