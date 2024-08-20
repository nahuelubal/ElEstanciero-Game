package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.dtos.game.SavedGame;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.services.DashboardService;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;

@Data
public class ManageLoadedGameView extends AbstractView {
	private SavedGame savedGame;
	private GameFlowView gameFlowView;

	public ManageLoadedGameView(SavedGame savedGame) {
		super();
		gameFlowView = new GameFlowView();
		this.savedGame = savedGame;
	}

	@Override
	public void render() {
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		ConsolePrinter.println("Decide what to do with the loaded game", new ConsoleConfig(Align.CENTER, true));
		ConsolePrinter.println("Game " + savedGame.getIdGame() + " - " + savedGame.getUpdatedAt(), new ConsoleConfig(Align.CENTER));
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		ConsolePrinter.println("What do you want to do?");
		ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "1." + ConsoleColor.RESET + " Load game");
		ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "2." + ConsoleColor.RESET + " Delete game");
		ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "3." + ConsoleColor.RESET + " Back");

		System.out.print("✎... ");
		String selectedOption = getScanner().nextLine();
		while (!InputValidator.isValidNumberInRange(selectedOption, 1, 3)) {
			ConsolePrinter.printlnColor("✖ Invalid number! You must type a number from 1 to 3 to continue.", ConsoleColor.BRIGHT_RED);
			System.out.print("✎... ");
			selectedOption = getScanner().nextLine();
		}

		switch (selectedOption) {
			case "1":
				Game.setInstance(getGameService().getGameById(savedGame.getIdGame()));
				getDashboardService().createInitialDashboard();
				getDashboardService().setPlayersIntoTheirBoxes(Game.getInstance().getPlayers());
				ConsolePrinter.println("Loading game...");
				gameFlowView.render();
				break;
			case "2":
				getGameService().deleteGameById(savedGame.getIdGame());
				ConsolePrinter.println("Deleting game...");
				ConsolePrinter.println("Press enter to back");
				System.out.print("▷  ");
				getScanner().nextLine();
				break;
		}
	}
}
