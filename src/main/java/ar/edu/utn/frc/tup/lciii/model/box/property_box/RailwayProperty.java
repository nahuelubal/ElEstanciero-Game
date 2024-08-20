package ar.edu.utn.frc.tup.lciii.model.box.property_box;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import ar.edu.utn.frc.tup.lciii.model.deeds.Railway;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class RailwayProperty extends PropertyBox {

	@Override
	public void actionBox(AbstractPlayer player) {
		ConsolePrinter.println("Railway Property!");

		Railway railway = (Railway) deed;
		Game game = Game.getInstance();

		if (player instanceof HumanPlayer) {
			if (player.hasProperty(railway)) {
				ConsolePrinter.println("The player" + player.getColor() + " has in his properties: " + railway.getName());
			} else {
				Optional<AbstractPlayer> ownerOptional = game.somePlayerHasProperty(railway);
				if (ownerOptional.isPresent()) {
					AbstractPlayer owner = ownerOptional.get();
					int railwayQuantity = owner.getPropertyTypeQuantity(railway);
					int rentValue = railway.getRent(railwayQuantity);
					showRentText(owner.getColor(), player.getColor(), rentValue, railway, player.getMoneyAvailable());
					player.pay(rentValue);
					owner.receive(rentValue);
					ConsolePrinter.println("The owner receives $" + rentValue);
				} else {
					showBuyText(railway);
					if (InputValidator.getYesNoAnswer()) {
						player.buyProperty(railway);
						showBoughtText(railway, player.getMoneyAvailable());
					} else {
						ConsolePrinter.println("Next Turn");
					}
				}
			}
		} else {
			player.buyProperty(railway);
		}
	}
}
