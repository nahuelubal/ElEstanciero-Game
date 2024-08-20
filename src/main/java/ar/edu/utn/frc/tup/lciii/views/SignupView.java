package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.auth.Auth;
import ar.edu.utn.frc.tup.lciii.model.user.User;
import ar.edu.utn.frc.tup.lciii.utils.ResponseHandler;

public class SignupView extends AbstractView {

	@Override
	public void render() {
		Auth auth = Auth.getInstance(getUserRepository(), getModelMapper());
		ResponseHandler responseHandler;
		do {
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("Register to play!", new ConsoleConfig(Align.CENTER, true));
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("Type your username: ");
			System.out.print("✎... ");
			String username = getScanner().nextLine();
			ConsolePrinter.println("Type your password: ");
			System.out.print("✎... ");
			String password = getScanner().nextLine();

			User newUser = new User(username, password);

			responseHandler = auth.register(newUser);
			if (responseHandler.getSuccess()) {
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.GREEN, new ConsoleConfig(Align.CENTER));
				ConsolePrinter.printlnColor("✓ Successfully registered user! " + newUser.getUserName(), ConsoleColor.BRIGHT_GREEN);
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.GREEN, new ConsoleConfig(Align.CENTER));
			} else {
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.RED, new ConsoleConfig(Align.CENTER));
				ConsolePrinter.printlnColor("Χ Registration failed! " + responseHandler.getMessage(), ConsoleColor.BRIGHT_RED);
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.RED, new ConsoleConfig(Align.CENTER));
			}
		} while (!responseHandler.getSuccess());
	}
}
