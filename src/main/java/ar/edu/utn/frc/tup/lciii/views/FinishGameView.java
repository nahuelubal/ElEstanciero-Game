package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import lombok.Data;

import java.util.Objects;
import java.util.Scanner;

@Data
public class FinishGameView extends AbstractView {
	public static final Integer USER_BANKRUPTED = 1;
	public static final Integer USER_WON = 2;
	public static final Integer SOMEONE_HAS_ENOUGH_MONEY_TO_WIN = 3;
	private final Integer reason;

	private PrincipalMenuView principalMenuView;

	public FinishGameView(Integer reason) {
		super();
		this.reason = reason;
		principalMenuView = new PrincipalMenuView();
	}

	@Override
	public void render() {
		if (Objects.equals(reason, USER_WON)) {
			ConsolePrinter.println("YOU WON");
			ConsolePrinter.println("Congratulations!");
		}
		if (Objects.equals(reason, USER_BANKRUPTED)) {
			ConsolePrinter.println("Oh no! You got bankrupt! :,(");
			ConsolePrinter.println("Get luck next time!");
		}
		if (Objects.equals(reason, SOMEONE_HAS_ENOUGH_MONEY_TO_WIN)) {
			ConsolePrinter.println("Somebody reached the money goal!");
			ConsolePrinter.println("Get luck next time!");
		}

		ConsolePrinter.printlnColor("Press enter to continue...", ConsoleColor.BRIGHT_MAGENTA);
		System.out.print("▷  ");
		getScanner().nextLine();

		// save game
		getGameService().saveOrUpdateGame(Game.getInstance());

		// render menu
		Game.getInstance().cleanInstance();
		principalMenuView.render();
	}
}
