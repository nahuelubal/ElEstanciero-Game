package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.*;

import java.util.List;

@Data
public class TaxBox extends AbstractBox {
	private int taxAmount;
	private String description;

	public TaxBox(Integer idBox, Integer boxNumber, List<AbstractPlayer> players, BoxType boxType) {
		super(idBox, boxNumber, players, boxType);
	}

	public TaxBox() {
		super();
	}

	@Override
	public void actionBox(AbstractPlayer player) {
		ConsolePrinter.println(getDescription() + "!");
		ConsolePrinter.println("The player " + player.getColor() + " must pay $" + this.taxAmount);
		player.pay(taxAmount);
		ConsolePrinter.println("Now the player has $" + player.getMoneyAvailable());
	}
}
