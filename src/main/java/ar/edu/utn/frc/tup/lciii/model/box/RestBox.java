package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Random;

@Data
@NoArgsConstructor
public class RestBox extends AbstractBox {
	public RestBox(Integer idBox, Integer boxNumber, List<AbstractPlayer> players, BoxType boxType) {
		super(idBox, boxNumber, players, boxType);
	}


	/**
	 * Al llegar a esta casilla, el jugador tiene derecho, si así lo desea, a quedarse por dos turnos,
	 * siempre que no saque doble en los dados. Es condición avisar antes de tirar los dados
	 * los deseos de quedarse.
	 *
	 * @param player
	 */
	@Override
	public void actionBox(AbstractPlayer player) {

		if (player instanceof HumanPlayer) {

			if (player.getRoundsStayQuantity() < 2) {
				ConsolePrinter.println("¿Do you want to rest?");
				ConsolePrinter.println("Before, roll dices");
				Game.getInstance().getDice().rollDices();
				boolean areDicesEqual = Game.getInstance().getDice().areDicesEquals();

				ConsolePrinter.println("Select Y (Yes) / N (No):");
				boolean wantsToRest = InputValidator.getYesNoAnswer();

				if (wantsToRest && !areDicesEqual) {
					player.setIsActive(false);
					player.addRoundsStayQuantity();
					return;
				}
			}
		}
		//EL bot siempre juega
		ConsolePrinter.println("The player is in the rest box");
		player.setIsActive(true);
		player.setRoundsStayQuantity(0);
	}
}
