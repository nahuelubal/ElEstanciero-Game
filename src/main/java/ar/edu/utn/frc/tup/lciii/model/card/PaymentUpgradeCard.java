package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.DeedType;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;

@Data
public class PaymentUpgradeCard extends AbstractCard {
	public PaymentUpgradeCard() {
	}

	@Override
	public void actionCard(AbstractPlayer player) {
		int totalValue = 0;
		for (AbstractDeed deed : player.getDeeds()) {

			if (deed.getDeedType().equals(DeedType.PROVINCE)) {
				Province province = (Province) deed;
				totalValue += province.getFarmQuantity() * 800;

				if (province.getHasRanch()) {
					totalValue += 4000;
				}
			}
		}

		player.pay(totalValue);
		ConsolePrinter.println("Player " + player.getColor() + " has paid $" + totalValue);
		ConsolePrinter.println("Money: $ " + player.getMoneyAvailable());
	}
}
