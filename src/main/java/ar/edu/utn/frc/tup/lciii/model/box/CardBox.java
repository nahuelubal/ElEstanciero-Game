package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor

public class CardBox extends AbstractBox {
	private Boolean isDestiny;

	public CardBox(Integer idBox, Integer boxNumber, List<AbstractPlayer> players, BoxType boxType) {
		super(idBox, boxNumber, players, boxType);
	}

	@Override
	public void actionBox(AbstractPlayer player) {
		ConsolePrinter.println("Card Box!");
		try {
			Dashboard dashboard = Dashboard.getInstance();
			AbstractCard card = isDestiny ? dashboard.getDestinyCards().takeCard() : dashboard.getLuckyCards().takeCard();

			if (card == null) {
				throw new NullPointerException("Card not found");
			}

			ConsolePrinter.println(card.getDescription());
			card.actionCard(player);

			if (isDestiny) {
				dashboard.getDestinyCards().putCard(card);
			} else {
				dashboard.getLuckyCards().putCard(card);
			}
		} catch (NullPointerException e) {
			System.err.println(e.getMessage());
		}
	}
}
