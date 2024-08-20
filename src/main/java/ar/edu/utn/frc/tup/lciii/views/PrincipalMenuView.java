package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.auth.Auth;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;

@Data
public class PrincipalMenuView extends AbstractView {
	NewGameView newGameView;
	LoadedGamesView loadedGamesView;
	SettingsView settingsView;

	public PrincipalMenuView() {
		super();
		newGameView = new NewGameView();
		loadedGamesView = new LoadedGamesView();
		settingsView = new SettingsView();
	}

	@Override
	public void render() {
		String selectedOption;
		do {
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("Principal Menu", new ConsoleConfig(Align.CENTER, true));
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("What do you want to do?");
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "1." + ConsoleColor.RESET + " New game");
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "2." + ConsoleColor.RESET + " Loaded games");
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "3." + ConsoleColor.RESET + " Settings");
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "4." + ConsoleColor.RESET + " Back");
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));

			System.out.print("✎... ");
			selectedOption = getScanner().nextLine();
			while (!InputValidator.isValidNumberInRange(selectedOption, 1, 4)) {
				ConsolePrinter.printlnColor("✖ Invalid number! You must type a number from 1 to 4 to continue.", ConsoleColor.BRIGHT_RED);
				System.out.print("✎... ");
				selectedOption = getScanner().nextLine();
			}

			switch (selectedOption) {
				case "1":
					newGameView.render();
					break;
				case "2":
					loadedGamesView.render();
					break;
				case "3":
					settingsView.render();
					break;
			}
		} while (!selectedOption.equals("4"));
		Auth auth = Auth.getInstance(getUserRepository(), getModelMapper());
		auth.logout();
	}
}
