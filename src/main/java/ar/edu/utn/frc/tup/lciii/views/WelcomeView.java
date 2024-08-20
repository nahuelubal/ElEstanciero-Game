package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import lombok.Data;

@Data
public class WelcomeView extends AbstractView {
	private LoginOrSignupView loginOrSignupView;

	public WelcomeView() {
		this.loginOrSignupView = new LoginOrSignupView();
	}

	@Override
	public void render() {
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('='), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		ConsolePrinter.println("TPI LAB III Presentation", new ConsoleConfig(Align.CENTER, true));
		ConsolePrinter.printlnColor("Welcome to the Estanciero game!", ConsoleColor.BRIGHT_MAGENTA, new ConsoleConfig(Align.CENTER));
		ConsolePrinter.printlnColor("Press enter to continue...", ConsoleColor.BRIGHT_MAGENTA, new ConsoleConfig(Align.CENTER));
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('='), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		System.out.print("▷  ");
		getScanner().nextLine();
		loginOrSignupView.render();
		System.out.println();
		ConsolePrinter.printlnColor("Thank you for playing Estanciero, we are waiting for you...", ConsoleColor.BRIGHT_CYAN);
	}
}
