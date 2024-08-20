package ar.edu.utn.frc.tup.lciii.model.box.property_box;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Optional;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ProvinceProperty extends PropertyBox {

	@Override
	public void actionBox(AbstractPlayer player) {
		ConsolePrinter.println("Province Property Box!");
		Province province = (Province) deed;
		Game game = Game.getInstance();

		if (player instanceof HumanPlayer) {
			if (player.hasProperty(province)) {
				ConsolePrinter.println("The player already has in his properties: " + province.getName());
			} else {
				Optional<AbstractPlayer> ownerOptional = game.somePlayerHasProperty(province);
				if (ownerOptional.isPresent()) {
					AbstractPlayer owner = ownerOptional.get();
					boolean hasEntireProvince = owner.hasEntireProvince(province);
					int rentValue = province.getRent(hasEntireProvince);

					showRentText(owner.getColor(), player.getColor(), rentValue, province, player.getMoneyAvailable());

					player.pay(rentValue);
					owner.receive(rentValue);

					ConsolePrinter.println("The owner receives $" + rentValue);
				} else {
					showBuyText(province);
					if (InputValidator.getYesNoAnswer()) {
						player.buyProperty(province);

						showBoughtText(province, player.getMoneyAvailable());

					} else {
						ConsolePrinter.println("Next turn!");
					}
				}
			}
		} else {
			if (player.hasProperty(province)) {
				ConsolePrinter.println("The player already has in his properties: " + province.getName());
			} else {
				Optional<AbstractPlayer> ownerOptional = game.somePlayerHasProperty(province);
				if (ownerOptional.isPresent()) {
					AbstractPlayer owner = ownerOptional.get();
					boolean hasEntireProvince = owner.hasEntireProvince(province);
					int rentValue = province.getRent(hasEntireProvince);

					showRentText(owner.getColor(), player.getColor(), rentValue, province, player.getMoneyAvailable());

					player.pay(rentValue);
					owner.receive(rentValue);
					ConsolePrinter.println("The owner receives $" + rentValue);
				} else {
					player.buyProperty(province);
				}
			}
		}
	}
}
