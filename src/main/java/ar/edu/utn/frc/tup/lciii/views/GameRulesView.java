package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;

public class GameRulesView extends AbstractView {

	@Override
	public void render() {
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		ConsolePrinter.println("Game rules", new ConsoleConfig(Align.CENTER, true));
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		ConsolePrinter.println("...");
		ConsolePrinter.println("...");
		ConsolePrinter.println("...");
		ConsolePrinter.println("...");
	}
}
