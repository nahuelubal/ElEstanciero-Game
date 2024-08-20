package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import ar.edu.utn.frc.tup.lciii.model.box.JailBox;
import ar.edu.utn.frc.tup.lciii.model.box.RestBox;
import ar.edu.utn.frc.tup.lciii.others.console_writer.*;
import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;

import java.util.*;

public class GameFlowView extends AbstractView {
	private final GameService gameService;
	private AbstractPlayer winner;

	public GameFlowView() {
		super();
		gameService = getGameService();
	}

	@Override
	public void render() {
		Game currentGame = Game.getInstance();
		Dashboard dashboard = Dashboard.getInstance();

		boolean isEveryOneBankRupt = false;
		boolean someoneHasEnoughMoneyToWin = false;
		boolean userWantsToStopPlaying = false;
		boolean userIsBankrupt = false;

		if (currentGame.getIdGame() == null) {
			rollDicesToGetPlayerOrder();

			GameEntity gameEntity = gameService.saveOrUpdateGame(currentGame);
			currentGame.setIdGame(gameEntity.getIdGame());

			Queue<AbstractPlayer> playerList = gameService.getAllPlayersByGameId(gameEntity.getIdGame());
			for (AbstractPlayer player : playerList) {
				AbstractPlayer obtainedPlayer = currentGame.getPlayerByColor(player.getColor());
				obtainedPlayer.setIdPlayer(player.getIdPlayer());
			}

			ConsolePrinter.printlnColor("Press enter to continue...", ConsoleColor.BRIGHT_MAGENTA);
			System.out.print("▷  ");
			getScanner().nextLine();
		}

		while (!isEveryOneBankRupt && !someoneHasEnoughMoneyToWin && !userWantsToStopPlaying && !userIsBankrupt) {
			AbstractPlayer currentPlayer = getNextPlayer();
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
			ConsolePrinter.println("Player " + ConsoleColor.valueOf(currentPlayer.getColor().toString()) + currentPlayer.getColor() + ConsoleColor.RESET + " turn", new ConsoleConfig(Align.CENTER, true));
			ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));

			dashboard.paintBoxes();
			Boolean playerAlreadyPlayed = false;
			if (!currentPlayer.isPrisoner() && currentPlayer.getIsActive()) {
				playerAlreadyPlayed = true;
				Boolean rollAgain;
				do {
					ConsolePrinter.printlnColor("Press enter to roll dices...", ConsoleColor.BRIGHT_MAGENTA);
					System.out.print("▷  ");
					getScanner().nextLine();

					Integer diceResult = getDice().rollDices();
					rollAgain = getDice().areDicesEquals();
					ConsolePrinter.printlnColor("Press enter to continue...", ConsoleColor.BRIGHT_MAGENTA);
					System.out.print("▷  ");
					getScanner().nextLine();

					dashboard.movePlayerByQuantity(currentPlayer, diceResult);
					ConsolePrinter.println("The player " + currentPlayer.getColor() + " fell in the box: " + (dashboard.findBoxByPlayer(currentPlayer).getIdBox() - 1));
					dashboard.paintBoxes();
					paintUserInfo(currentPlayer);
					ConsolePrinter.printlnColor("Press enter to continue...", ConsoleColor.BRIGHT_MAGENTA);
					System.out.print("▷  ");
					getScanner().nextLine();

					dashboard.findBoxByPlayer(currentPlayer).actionBox(currentPlayer);
					ConsolePrinter.printlnColor("Press enter to continue...", ConsoleColor.BRIGHT_MAGENTA);
					System.out.print("▷  ");
					getScanner().nextLine();

					if (!currentGame.getPlayers().contains(currentPlayer)) {
						userIsBankrupt = currentPlayer instanceof HumanPlayer;
						break;
					}
				} while (rollAgain);
			}

			if (currentPlayer.isPrisoner() && !playerAlreadyPlayed) {
				dashboard.findBoxByType(JailBox.class).actionBox(currentPlayer);
			}

			if (!currentPlayer.getIsActive() && !playerAlreadyPlayed) {
				dashboard.findBoxByType(RestBox.class).actionBox(currentPlayer);
			}

			if (Game.getInstance().getIsWinByMoneyObjective()) {
				someoneHasEnoughMoneyToWin = someoneHasEnoughMoneyToWin();
			}

			isEveryOneBankRupt = Game.getInstance().getPlayers().size() == 1;

			if (someoneHasEnoughMoneyToWin || userIsBankrupt || isEveryOneBankRupt) {
				Integer reason;
				if (someoneHasEnoughMoneyToWin)
					if (winner instanceof HumanPlayer)
						reason = FinishGameView.USER_WON;
					else
						reason = FinishGameView.SOMEONE_HAS_ENOUGH_MONEY_TO_WIN;
				else if (userIsBankrupt)
					reason = FinishGameView.USER_BANKRUPTED;
				else
					reason = FinishGameView.USER_WON;

				new FinishGameView(reason).render();
			}

			if (currentPlayer.getPlayerOrder().equals(Game.getInstance().getPlayers().size())) {
				gameService.saveOrUpdateGame(currentGame);
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.GREEN, new ConsoleConfig(Align.CENTER));
				ConsolePrinter.printlnColor("✓ Game saved!", ConsoleColor.BRIGHT_GREEN);
				ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.GREEN, new ConsoleConfig(Align.CENTER));

				ConsolePrinter.println("Do you want to continue? Y (Yes) / N (No):", new ConsoleConfig(Align.LEFT, true));
				if (!InputValidator.getYesNoAnswer()) {
					userWantsToStopPlaying = true;
				}
			}
		}
	}

	private void paintUserInfo(AbstractPlayer player) {
		ConsolePrinter.println("Money: $" + player.getMoneyAvailable());

		StringBuilder properties = new StringBuilder();
		properties.append("Properties: ");
		if (!player.getDeeds().isEmpty()) {
			int i = 0;
			for (AbstractDeed deed : player.getDeeds()) {
				properties.append(deed.getName());
				if (i < player.getDeeds().size() - 1) {
					properties.append(", ");
				}
				i++;
			}
		} else {
			properties.append("Does not yet have");
		}

		ConsolePrinter.println(properties.toString());
	}

	private void rollDicesToGetPlayerOrder() {
		ConsolePrinter.println("We are going to roll the dices to get player order", new ConsoleConfig(Align.LEFT, true));

		List<AbstractPlayer> playerListAux = new ArrayList<>(Game.getInstance().getPlayers());
		for (AbstractPlayer player : playerListAux) {
			ConsolePrinter.printlnColor("Rolling dices for player " + player.getColor(), ConsoleColor.CYAN);
			player.setPlayerOrder(getDice().rollDices());
		}

		playerListAux.sort(Comparator.comparing(AbstractPlayer::getPlayerOrder).reversed());

		ConsolePrinter.println("");
		ConsolePrinter.println("The order is:", new ConsoleConfig(Align.LEFT, true));
		for (int i = 0; i < playerListAux.size(); i++) {
			playerListAux.get(i).setPlayerOrder(i + 1);
			ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA.toString() + (i + 1) + "." + ConsoleColor.RESET + " Player " + playerListAux.get(i).getColor());
		}
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		Game.getInstance().setPlayers(new LinkedList<>(playerListAux));
	}

	private Boolean someoneHasEnoughMoneyToWin() {
		for (AbstractPlayer player : Game.getInstance().getPlayers()) {
			if (player.getMoneyAvailable() >= Game.getInstance().getMoneyObjectiveToWin()) {
				winner = player;
				return true;
			}
		}
		return false;
	}

	private AbstractPlayer getNextPlayer() {
		AbstractPlayer player = Game.getInstance().getPlayers().poll();
		Game.getInstance().getPlayers().add(player);
		return player;
	}
}
