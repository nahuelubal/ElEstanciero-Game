package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.*;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;

@Data
public class DefineMoneyToWinView extends AbstractView {

	private ActiveGameView activeGameView;

	public DefineMoneyToWinView() {
		this.activeGameView = new ActiveGameView();
	}

	@Override
	public void render() {
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		ConsolePrinter.printlnBold("You can define an amount to reach to win the game");
		ConsolePrinter.println("Do you want to define one? Y (Yes) / N (No)");

		if (InputValidator.getYesNoAnswer()) {
			ConsolePrinter.println("Enter the amount of money to reach to win the game:");
			System.out.print("✎... ");
			String moneyObjective = getScanner().nextLine();
			while (!InputValidator.isValidNumber(moneyObjective)) {
				ConsolePrinter.printlnColor("✖ Invalid number!", ConsoleColor.BRIGHT_RED);
				System.out.print("✎... ");
				moneyObjective = getScanner().nextLine();
			}

			Game.getInstance().setMoneyObjectiveToWin(Integer.parseInt(moneyObjective));
			Game.getInstance().setIsWinByMoneyObjective(true);
		}

		activeGameView.render();
	}
}
