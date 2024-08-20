package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.auth.Auth;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import ar.edu.utn.frc.tup.lciii.utils.ResponseHandler;

public class ChangePasswordView extends AbstractView {

	@Override
	public void render() {
		Auth auth = Auth.getInstance(getUserRepository(), getModelMapper());
		ResponseHandler responseHandler;
		boolean wantsToChangeThePassword = false;
		do {
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("Change password", new ConsoleConfig(Align.CENTER, true));
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("Type your current password: ");
			System.out.print("✎... ");
			String currentPassword = getScanner().nextLine();
			ConsolePrinter.println("Type your new password: ");
			System.out.print("✎... ");
			String newPassword = getScanner().nextLine();

			responseHandler = auth.changePassword(currentPassword, newPassword);
			if (responseHandler.getSuccess()) {
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.GREEN, new ConsoleConfig(Align.CENTER));
				ConsolePrinter.printlnColor("✓ Successfully password changed!", ConsoleColor.BRIGHT_GREEN);
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.GREEN, new ConsoleConfig(Align.CENTER));
			} else {
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.RED, new ConsoleConfig(Align.CENTER));
				ConsolePrinter.printlnColor("Χ Password change failed! " + responseHandler.getMessage(), ConsoleColor.BRIGHT_RED);
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.RED, new ConsoleConfig(Align.CENTER));

				ConsolePrinter.println("Do you want to change the password? Y (Yes) / N (No)");
				wantsToChangeThePassword = InputValidator.getYesNoAnswer();
			}
		} while (!responseHandler.getSuccess() && wantsToChangeThePassword);
	}
}
