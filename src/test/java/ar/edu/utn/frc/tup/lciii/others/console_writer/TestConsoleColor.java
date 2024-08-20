package ar.edu.utn.frc.tup.lciii.others.console_writer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestConsoleColor {
	@Test
	void enumValues() {
		ConsoleColor[] expectedValues = {
				ConsoleColor.RESET,
				ConsoleColor.BLACK,
				ConsoleColor.RED,
				ConsoleColor.GREEN,
				ConsoleColor.YELLOW,
				ConsoleColor.BLUE,
				ConsoleColor.VIOLET,
				ConsoleColor.CYAN,
				ConsoleColor.WHITE,
				ConsoleColor.BRIGHT_BLACK,
				ConsoleColor.BRIGHT_RED,
				ConsoleColor.BRIGHT_GREEN,
				ConsoleColor.BRIGHT_YELLOW,
				ConsoleColor.BRIGHT_BLUE,
				ConsoleColor.BRIGHT_MAGENTA,
				ConsoleColor.BRIGHT_CYAN,
				ConsoleColor.BRIGHT_WHITE,
				ConsoleColor.BLACK_BACKGROUND,
				ConsoleColor.RED_BACKGROUND,
				ConsoleColor.GREEN_BACKGROUND,
				ConsoleColor.YELLOW_BACKGROUND,
				ConsoleColor.BLUE_BACKGROUND,
				ConsoleColor.VIOLET_BACKGROUND,
				ConsoleColor.CYAN_BACKGROUND,
				ConsoleColor.WHITE_BACKGROUND,
				ConsoleColor.ORANGE,
				ConsoleColor.ORANGE_BACKGROUND
		};

		assertArrayEquals(ConsoleColor.values(), expectedValues);
	}

	@Test
	void valueOf() {
		assertEquals(ConsoleColor.RESET, ConsoleColor.valueOf("RESET"));
		assertEquals(ConsoleColor.BLACK, ConsoleColor.valueOf("BLACK"));
		assertEquals(ConsoleColor.RED, ConsoleColor.valueOf("RED"));
		assertEquals(ConsoleColor.GREEN, ConsoleColor.valueOf("GREEN"));
		assertEquals(ConsoleColor.YELLOW, ConsoleColor.valueOf("YELLOW"));
		assertEquals(ConsoleColor.BLUE, ConsoleColor.valueOf("BLUE"));
		assertEquals(ConsoleColor.VIOLET, ConsoleColor.valueOf("VIOLET"));
		assertEquals(ConsoleColor.CYAN, ConsoleColor.valueOf("CYAN"));
		assertEquals(ConsoleColor.WHITE, ConsoleColor.valueOf("WHITE"));
		assertEquals(ConsoleColor.BRIGHT_BLACK, ConsoleColor.valueOf("BRIGHT_BLACK"));
		assertEquals(ConsoleColor.BRIGHT_RED, ConsoleColor.valueOf("BRIGHT_RED"));
		assertEquals(ConsoleColor.BRIGHT_GREEN, ConsoleColor.valueOf("BRIGHT_GREEN"));
		assertEquals(ConsoleColor.BRIGHT_YELLOW, ConsoleColor.valueOf("BRIGHT_YELLOW"));
		assertEquals(ConsoleColor.BRIGHT_BLUE, ConsoleColor.valueOf("BRIGHT_BLUE"));
		assertEquals(ConsoleColor.BRIGHT_MAGENTA, ConsoleColor.valueOf("BRIGHT_MAGENTA"));
		assertEquals(ConsoleColor.BRIGHT_CYAN, ConsoleColor.valueOf("BRIGHT_CYAN"));
		assertEquals(ConsoleColor.BRIGHT_WHITE, ConsoleColor.valueOf("BRIGHT_WHITE"));
		assertEquals(ConsoleColor.BLACK_BACKGROUND, ConsoleColor.valueOf("BLACK_BACKGROUND"));
		assertEquals(ConsoleColor.RED_BACKGROUND, ConsoleColor.valueOf("RED_BACKGROUND"));
		assertEquals(ConsoleColor.GREEN_BACKGROUND, ConsoleColor.valueOf("GREEN_BACKGROUND"));
		assertEquals(ConsoleColor.YELLOW_BACKGROUND, ConsoleColor.valueOf("YELLOW_BACKGROUND"));
		assertEquals(ConsoleColor.BLUE_BACKGROUND, ConsoleColor.valueOf("BLUE_BACKGROUND"));
		assertEquals(ConsoleColor.VIOLET_BACKGROUND, ConsoleColor.valueOf("VIOLET_BACKGROUND"));
		assertEquals(ConsoleColor.CYAN_BACKGROUND, ConsoleColor.valueOf("CYAN_BACKGROUND"));
		assertEquals(ConsoleColor.WHITE_BACKGROUND, ConsoleColor.valueOf("WHITE_BACKGROUND"));
		assertEquals(ConsoleColor.ORANGE, ConsoleColor.valueOf("ORANGE"));
		assertEquals(ConsoleColor.ORANGE_BACKGROUND, ConsoleColor.valueOf("ORANGE_BACKGROUND"));

	}

}