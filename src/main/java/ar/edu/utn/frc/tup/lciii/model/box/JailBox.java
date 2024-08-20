package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Scanner;

@Data
@NoArgsConstructor
public class JailBox extends AbstractBox {
	public JailBox(Integer idBox, Integer boxNumber, List<AbstractPlayer> players, BoxType boxType) {
		super(idBox, boxNumber, players, boxType);
	}

	/**
	 * Si eres enviado a la cárcel o en el curso ordinario del juego
	 * aterrizas en esta casilla, eres prisionero y las formas de salir son:
	 * Pagando $1000.
	 * Sacando dobles en los dados.
	 * Utilizando las tarjetas de suerte o destino para salir gratis.
	 * Esperando 2 turnos.(si no puede o no quiere con ninguna de las opciones anteriores, debe esperar )
	 *
	 * @param player
	 */
	@Override
	public void actionBox(AbstractPlayer player) {
		player.setPrisoner(true);

		if (player.getRoundsStayQuantity() < 2) {

			if (player instanceof HumanPlayer) {
				Scanner scanner = new Scanner(System.in);

				String selectedOption;
				ConsolePrinter.printlnBold("First you must choose the options");
				ConsolePrinter.println("Which option do you want to get out of the jail?");
				ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "1." + ConsoleColor.RESET + " Pay $1000.");
				ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "2." + ConsoleColor.RESET + " Try to get double in the dice.");
				if (player.isHasHabeasCorpus()) {
					ConsolePrinter.println(ConsoleColor.BRIGHT_MAGENTA + "3." + ConsoleColor.RESET + " Use the card to get out of the jail.");
				}

				System.out.print("✎... ");
				selectedOption = scanner.nextLine();
				while (!InputValidator.isValidNumberInRange(selectedOption, 1, 3)) {
					ConsolePrinter.printlnColor("✖ Invalid number! You must type a number from 1 to 3 to continue.", ConsoleColor.BRIGHT_RED);
					System.out.print("✎... ");
					selectedOption = scanner.nextLine();
				}

				switch (selectedOption) {
					case "1":
						player.pay(1000);
						player.setPrisoner(false);
						player.setRoundsStayQuantity(0);
						ConsolePrinter.println("You have successfully paid $1000");
						ConsolePrinter.println("Your actual money available is $" + player.getMoneyAvailable());
						return;
					case "2":
						Game.getInstance().getDice().rollDices();
						if (Game.getInstance().getDice().areDicesEquals()) {
							player.setPrisoner(false);
							player.setRoundsStayQuantity(0);
							ConsolePrinter.println("You obtained a double in the dice, you got out of the jail!");
							return;
						} else {
							ConsolePrinter.println("You couldn't obtain a double in the dice, you can´t get out of the jail!");
							break;
						}
					case "3":
						player.setPrisoner(false);
						player.setRoundsStayQuantity(0);
						player.setHasHabeasCorpus(false);
						if (Dashboard.getInstance().getDestinyCards().getTransitionalCards().isEmpty()) {
							Dashboard.getInstance().getLuckyCards().putHabeasCorpus();
						} else if (Dashboard.getInstance().getLuckyCards().getTransitionalCards().isEmpty()) {
							Dashboard.getInstance().getDestinyCards().putHabeasCorpus();
						}
						ConsolePrinter.println("You used the special card to get out of the jail!");
						ConsolePrinter.println("The card is back in the deck");
						return;
					default:
				}
				player.addRoundsStayQuantity();
			} else {
				if (player.isHasHabeasCorpus()) {
					player.setPrisoner(false);
					player.setRoundsStayQuantity(0);
					player.setHasHabeasCorpus(false);
					if (Dashboard.getInstance().getDestinyCards().getTransitionalCards().isEmpty()) {
						Dashboard.getInstance().getLuckyCards().putHabeasCorpus();
					} else if (Dashboard.getInstance().getLuckyCards().getTransitionalCards().isEmpty()) {
						Dashboard.getInstance().getDestinyCards().putHabeasCorpus();
					}
					ConsolePrinter.println("Player used the special card to get out of the jail!");
					ConsolePrinter.println("The card is back in the deck");
				} else {
					player.pay(1000);
					player.setPrisoner(false);
					player.setRoundsStayQuantity(0);
					ConsolePrinter.println("Player have successfully paid $1000 for getting out of the jail!");
					ConsolePrinter.println("Now, he has $" + player.getMoneyAvailable());
				}
			}
		} else {
			player.setPrisoner(false);
		}
	}
}


