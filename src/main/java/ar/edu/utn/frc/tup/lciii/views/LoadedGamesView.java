package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.dtos.game.SavedGame;
import ar.edu.utn.frc.tup.lciii.model.session.Session;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;

import java.util.List;

@Data
public class LoadedGamesView extends AbstractView {

	private ManageLoadedGameView manageLoadedGameView;

	public LoadedGamesView() {
		super();
		manageLoadedGameView = new ManageLoadedGameView(null);
	}

	@Override
	public void render() {
		String selectedOption;
		int optionsQuantity;
		do {
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("Here you can see the saved games.", new ConsoleConfig(Align.CENTER, true));
			ConsolePrinter.println("Select one to see more options", new ConsoleConfig(Align.CENTER));
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));

			List<SavedGame> gameListSaved = getGameService().getGamesByUserId(Session.getUser().getIdUser());
			optionsQuantity = gameListSaved.size() + 1;
			if (gameListSaved.isEmpty()) {
				ConsolePrinter.println("You don't have any saved game yet.");
				ConsolePrinter.println("Press enter to back...");
				System.out.print("▷  ");
				getScanner().nextLine();
				break;
			} else {
				ConsolePrinter.println("Select a game");

				for (int i = 0; i < gameListSaved.size(); i++) {
					SavedGame savedGame = gameListSaved.get(i);
					ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA.toString() + (i + 1) + "." + ConsoleColor.RESET + " Game " + savedGame.getIdGame() + " - " + savedGame.getUpdatedAt());
				}

				System.out.print("✎... ");
				selectedOption = getScanner().nextLine();
				while (!InputValidator.isValidNumberInRange(selectedOption, 1, optionsQuantity)) {
					ConsolePrinter.printlnColor("✖ Invalid number! You must type a number from 1 to " + optionsQuantity + " to continue.", ConsoleColor.BRIGHT_RED);
					System.out.print("✎... ");
					selectedOption = getScanner().nextLine();
				}

				manageLoadedGameView.setSavedGame(gameListSaved.get(Integer.parseInt(selectedOption) - 1));
				manageLoadedGameView.render();
				selectedOption = Integer.toString(optionsQuantity);
			}
		} while (!selectedOption.equals(Integer.toString(optionsQuantity)));
	}
}
