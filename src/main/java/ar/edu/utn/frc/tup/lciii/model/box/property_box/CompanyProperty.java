package ar.edu.utn.frc.tup.lciii.model.box.property_box;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;

import ar.edu.utn.frc.tup.lciii.model.config.Game;

import ar.edu.utn.frc.tup.lciii.model.deeds.Company;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
public class CompanyProperty extends PropertyBox {

	@Override
	public void actionBox(AbstractPlayer player) {
		ConsolePrinter.println("Company Property!");

		Company company = (Company) deed;
		Game game = Game.getInstance();

		if (player instanceof HumanPlayer) {
			if (player.hasProperty(company)) {
				ConsolePrinter.println("The player already has in his properties: " + company.getName());
			} else {
				Optional<AbstractPlayer> ownerOptional = game.somePlayerHasProperty(company);
				if (ownerOptional.isPresent()) {
					AbstractPlayer owner = ownerOptional.get();
					int quantity = owner.getPropertyTypeQuantity(company);
					int rentValue = company.getRent(quantity, game.getDice().getAddition());
					showRentText(owner.getColor(), player.getColor(), rentValue, company, player.getMoneyAvailable());
					player.pay(rentValue);
					owner.receive(rentValue);
				} else {
					showBuyText(company);

					if (InputValidator.getYesNoAnswer()) {
						player.buyProperty(company);
						showBoughtText(company, player.getMoneyAvailable());
					} else {
						ConsolePrinter.println("Next turn!");
					}
				}

			}
		} else {
			player.buyProperty(company);
		}
	}
}