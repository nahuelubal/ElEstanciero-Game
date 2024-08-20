package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;

@Data
public class ChooseDifficultyView extends AbstractView {

	private ChooseColorView chooseColorView;

	public ChooseDifficultyView() {
		super();
		this.chooseColorView = new ChooseColorView();
	}

	@Override
	public void render() {
		String selectedOption;
		ConsolePrinter.printlnBold("First you must choose the difficulty");
		ConsolePrinter.println("Which difficulty do you want?");
		ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "1." + ConsoleColor.RESET + " Easy");
		ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "2." + ConsoleColor.RESET + " Medium");
		ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "3." + ConsoleColor.RESET + " Hard");
		ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "4." + ConsoleColor.RESET + " Back");

		System.out.print("✎... ");
		selectedOption = getScanner().nextLine();
		while (!InputValidator.isValidNumberInRange(selectedOption, 1, 4)) {
			ConsolePrinter.printlnColor("✖ Invalid number! You must type a number from 1 to 4 to continue.", ConsoleColor.BRIGHT_RED);
			System.out.print("✎... ");
			selectedOption = getScanner().nextLine();
		}

		if (!selectedOption.equals("4")) {
			Difficulty selectedDifficulty = Difficulty.values()[Integer.parseInt(selectedOption) - 1];
			ConsolePrinter.println("Perfect! You will play on the " + selectedDifficulty.name() + " difficulty");
			Game.getInstance().setDifficulty(selectedDifficulty);

			chooseColorView.render();
		}
	}
}
