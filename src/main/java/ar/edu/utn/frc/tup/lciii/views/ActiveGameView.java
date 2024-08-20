package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.services.DashboardService;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import lombok.Data;

import java.util.Objects;
import java.util.Queue;

@Data
public class ActiveGameView extends AbstractView {

	private GameFlowView gameFlowView;

	public ActiveGameView() {
		super();
		gameFlowView = new GameFlowView();
	}

	@Override
	public void render() {
		Game currentGame = Game.getInstance();

		//If it is not a loaded game (Create new game)
		if (Objects.isNull(currentGame.getIdGame())) {
			// Set players
			Queue<AbstractPlayer> createdPlayers = getGameService().createPlayers(currentGame.getDifficulty());
			for (AbstractPlayer player : createdPlayers) {
				currentGame.getPlayers().add(player);
			}

			// Set dashboard and players into start box
			getDashboardService().createInitialDashboard();
			getDashboardService().setPlayersIntoStartBox(currentGame.getPlayers());

			currentGame.setDashboard(Dashboard.getInstance());
		}

		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		ConsolePrinter.println("To play! Enjoy it", new ConsoleConfig(Align.CENTER, true));
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		gameFlowView.render();
	}
}
