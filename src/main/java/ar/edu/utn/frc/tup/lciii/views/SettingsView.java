package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;

@Data
public class SettingsView extends AbstractView {
	GameRulesView gameRulesView;
	ChangePasswordView changePasswordView;

	public SettingsView() {
		super();
		gameRulesView = new GameRulesView();
		changePasswordView = new ChangePasswordView();
	}

	@Override
	public void render() {
		String selectedOption;
		do {
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("Settings Menu", new ConsoleConfig(Align.CENTER, true));
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("What do you want to do?");
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "1." + ConsoleColor.RESET + " Game rules");
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "2." + ConsoleColor.RESET + " Change password");
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "3." + ConsoleColor.RESET + " Back");
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));

			System.out.print("✎... ");
			selectedOption = getScanner().nextLine();
			while (!InputValidator.isValidNumberInRange(selectedOption, 1, 3)) {
				ConsolePrinter.printlnColor("✖ Invalid number! You must type a number from 1 to 3 to continue.", ConsoleColor.BRIGHT_RED);
				System.out.print("✎... ");
				selectedOption = getScanner().nextLine();
			}

			switch (selectedOption) {
				case "1":
					gameRulesView.render();
					break;
				case "2":
					changePasswordView.render();
					break;
			}
		} while (!selectedOption.equals("3"));
	}
}
