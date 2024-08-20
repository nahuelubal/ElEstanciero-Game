package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;

@Data
public class MovementQuantityCard extends AbstractCard {

	public MovementQuantityCard() {
	}

	@Override
	public void actionCard(AbstractPlayer player) {
		ConsolePrinter.println("The player " + player.getColor() + "moved " + super.getValue() + " boxes back");
		Dashboard.getInstance().movePlayerByQuantity(player, (super.getValue() * -1));
	}
}
