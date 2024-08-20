package ar.edu.utn.frc.tup.lciii.others.console_writer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TestLetterByLetterPrinter {

	@Test
	void println() {
		String text = "Text";
		assertDoesNotThrow(() -> LetterByLetterPrinter.println(text));
	}

	@Test
	public void testPrint() {
		String text = "Text";
		assertDoesNotThrow(() -> LetterByLetterPrinter.print(text));
	}
}