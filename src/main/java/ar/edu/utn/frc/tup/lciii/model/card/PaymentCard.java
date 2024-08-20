package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;


@Data
public class PaymentCard extends AbstractCard {
	public PaymentCard() {
	}

	@Override
	public void actionCard(AbstractPlayer player) {
		player.pay(super.getValue());
		ConsolePrinter.println("The player " + player.getColor() + " paid $" + super.getValue());
		ConsolePrinter.println("Money: $ " + player.getMoneyAvailable());
	}
}
