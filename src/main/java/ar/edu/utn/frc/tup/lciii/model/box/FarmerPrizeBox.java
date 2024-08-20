package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;

import java.util.List;

@Data
public class FarmerPrizeBox extends AbstractBox {
	private final int amount = 7500;

	public FarmerPrizeBox(Integer idBox, Integer boxNumber, List<AbstractPlayer> players, BoxType boxType) {
		super(idBox, boxNumber, players, boxType);
	}

	public FarmerPrizeBox() {

	}

	@Override
	public void actionBox(AbstractPlayer player) {
		ConsolePrinter.println("Farmer Prize!");
		ConsolePrinter.println("The player " + player.getColor() + " received $" + this.amount);
		player.receive(this.amount);
		ConsolePrinter.println("Money: $" + player.getMoneyAvailable());
	}
}
