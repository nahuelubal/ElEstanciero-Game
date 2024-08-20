package ar.edu.utn.frc.tup.lciii.others.console_writer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mockStatic;

class TestConsolePrinter {
	private static final String ANSI_REGEX = "\\u001B\\[[;\\d]*m";
	private static final String BOLD = "\033[0;1m";
	private static final String RESET_BOLD = "\033[22m";
	private Method applyStringMethod;
	private Method getStringMethod;

	@BeforeEach
	public void setUp() throws NoSuchMethodException {
		applyStringMethod = ConsolePrinter.class.getDeclaredMethod("applyString", StringBuilder.class, String.class, String.class);
		applyStringMethod.setAccessible(true);
		getStringMethod = ConsolePrinter.class.getDeclaredMethod("getString", String.class, ConsoleConfig.class, ConsoleColor.class);
		getStringMethod.setAccessible(true);
	}

	@Test
	void testFindCharacterIndices() {
		StringBuilder stringBuilder = new StringBuilder("TPI");
		List<Integer> indices = ConsolePrinter.findCharacterIndices(stringBuilder, "T");
		assertEquals(3, indices.size() + 1, "This list should contain 3 elements");
		assertEquals(0, indices.get(0), "The first input should be T");
	}

	@Test
	void testFindCharacterIndicesNotFound() {
		StringBuilder stringBuilder = new StringBuilder("TPI");
		List<Integer> indices = ConsolePrinter.findCharacterIndices(stringBuilder, "a");
		assertEquals(false, indices.contains("a"), "The list does not contain 'a'");
		assertEquals(-1, indices.get(0), "A negative index can not be found");
	}

	@Test
	void testApplyColor() throws InvocationTargetException, IllegalAccessException {
		StringBuilder stringBuilder = new StringBuilder("◊ Hello, World! ◊");
		ConsoleColor consoleColor = ConsoleColor.RED;

		applyStringMethod.invoke(null, stringBuilder, consoleColor.toString(), ConsoleColor.RESET.toString());

		assertEquals("◊" + ConsoleColor.RED + " Hello, World! " + ConsoleColor.RESET + "◊", stringBuilder.toString());

	}

	@Test
	void testApplyBold() throws InvocationTargetException, IllegalAccessException {
		StringBuilder stringBuilder = new StringBuilder("◊ Hello, World! ◊");

		applyStringMethod.invoke(null, stringBuilder, BOLD, RESET_BOLD);

		assertEquals("◊" + BOLD + " Hello, World! " + RESET_BOLD + "◊", stringBuilder.toString());

	}

	@Test
	void testGetString() throws InvocationTargetException, IllegalAccessException {
		String stringToPrint = "Hello, World!";
		ConsoleConfig consoleConfig = new ConsoleConfig(Align.LEFT, false, 20);
		ConsoleColor consoleColor = ConsoleColor.BLUE;

		StringBuilder result = (StringBuilder) getStringMethod.invoke(null, stringToPrint, consoleConfig, consoleColor);

		String expectedString = "◊" + ConsoleColor.BLUE + "  Hello, World!   " + ConsoleColor.RESET + "◊";

		assertEquals(expectedString, result.toString());
	}

	@Test
	void printlnCentered() {
	}

	@Test
	void printlnBold() throws InvocationTargetException, IllegalAccessException {
		String stringToPrint = "Hello, World!";

		StringBuilder stringBuilder = (StringBuilder) getStringMethod.invoke(null, stringToPrint, new ConsoleConfig(Align.LEFT, true, 14), null);
		ConsolePrinter.println(stringToPrint, new ConsoleConfig(Align.LEFT, true, 14));

		String expectedOutput = "◊" + BOLD + "  Hello, World! " + RESET_BOLD + "◊";
		assertEquals(expectedOutput, stringBuilder.toString());
	}

	@Test
	void testPrintlnColor() throws InvocationTargetException, IllegalAccessException {
		String stringToPrint = "Hello, World!";

		StringBuilder stringBuilder = (StringBuilder) getStringMethod.invoke(null, stringToPrint, new ConsoleConfig(Align.LEFT, false, 14), null);
		ConsolePrinter.printlnColor(stringToPrint, ConsoleColor.BLUE, new ConsoleConfig(Align.LEFT, false, 14));

		String expectedOutput = "◊  Hello, World! ◊";
		assertEquals(expectedOutput, stringBuilder.toString());
	}

	@Test
	void buildLine() {
		char character = '=';
		int length = 4;
		String string ="====";
		assertEquals(string, ConsolePrinter.buildLine(character,length));
	}
}