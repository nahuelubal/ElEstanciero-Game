package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;

@Data
public class ChargeCard extends AbstractCard {

	public ChargeCard() {
	}

	@Override
	public void actionCard(AbstractPlayer player) {
		ConsolePrinter.println("The player " + player.getColor() + " receives $" + super.getValue());
		player.receive(super.getValue());
		ConsolePrinter.println("Money: $ " + player.getMoneyAvailable());
	}
}
