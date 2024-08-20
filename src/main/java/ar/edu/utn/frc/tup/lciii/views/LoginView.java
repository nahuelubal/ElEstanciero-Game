package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.auth.Auth;
import ar.edu.utn.frc.tup.lciii.model.user.User;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import ar.edu.utn.frc.tup.lciii.utils.ResponseHandler;
import lombok.Data;

@Data
public class LoginView extends AbstractView {

	private SignupView signupView;
	private PrincipalMenuView principalMenuView;

	public LoginView() {
		this.signupView = new SignupView();
		this.principalMenuView = new PrincipalMenuView();
	}

	@Override
	public void render() {
		Auth auth = Auth.getInstance(getUserRepository(), getModelMapper());
		ResponseHandler responseHandler;
		do {
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("Login with your account!", new ConsoleConfig(Align.CENTER, true));
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("Type your username: ");
			System.out.print("✎... ");
			String username = getScanner().nextLine();
			ConsolePrinter.println("Type your password: ");
			System.out.print("✎... ");
			String password = getScanner().nextLine();

			User newUser = new User(username, password);

			responseHandler = auth.login(newUser);
			if (responseHandler.getSuccess()) {
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.GREEN, new ConsoleConfig(Align.CENTER));
				ConsolePrinter.printlnColor("✓ Successfully login! " + newUser.getUserName(), ConsoleColor.BRIGHT_GREEN);
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.GREEN, new ConsoleConfig(Align.CENTER));
				principalMenuView.render();
			} else {
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.RED, new ConsoleConfig(Align.CENTER));
				ConsolePrinter.printlnColor("Χ Login failed! " + responseHandler.getMessage(), ConsoleColor.BRIGHT_RED);
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.RED, new ConsoleConfig(Align.CENTER));

				ConsolePrinter.printlnBold("Do you have an account? Y/N");
				Boolean answer = InputValidator.getYesNoAnswer();

				if (!answer) {
					signupView.render();
				}
			}
		} while (!responseHandler.getSuccess());
	}
}

