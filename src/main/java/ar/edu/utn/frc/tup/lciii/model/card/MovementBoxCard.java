package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;

@Data
public class MovementBoxCard extends AbstractCard {
	public MovementBoxCard() {
	}

	@Override
	public void actionCard(AbstractPlayer player) {
		ConsolePrinter.println("The player " + player.getColor() + " moved to the box " + (super.getBoxId() - 1));

		Dashboard.getInstance().movePlayerByBox(player, super.getBoxId());
	}


}
