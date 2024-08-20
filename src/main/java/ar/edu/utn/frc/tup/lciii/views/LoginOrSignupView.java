package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;

@Data
public class LoginOrSignupView extends AbstractView {

	private LoginView loginView;
	private SignupView signupView;

	public LoginOrSignupView() {
		this.loginView = new LoginView();
		this.signupView = new SignupView();
	}

	@Override
	public void render() {
		String selectedOption;
		do {
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("To start you must log in with your account.", new ConsoleConfig(Align.CENTER, true));
			ConsolePrinter.println("If you don't have one, you can register", new ConsoleConfig(Align.CENTER));
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("What do you want to do?");
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "1." + ConsoleColor.RESET + " Login");
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "2." + ConsoleColor.RESET + " Sign up");
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "3." + ConsoleColor.RESET + " Exit");
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
					loginView.render();
					break;
				case "2":
					signupView.render();
					break;
			}
		} while (!selectedOption.equals("3"));
	}
}
