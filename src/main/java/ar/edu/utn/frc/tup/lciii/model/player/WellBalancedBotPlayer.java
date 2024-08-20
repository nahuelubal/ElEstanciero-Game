package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.DeedType;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WellBalancedBotPlayer extends BotPlayer {
	private Game game = Game.getInstance();

	@Override
	public void buyProperty(AbstractDeed property) {
		boolean shouldBuy = false;

		if (gameStyle.getProvincesToPrioritize().contains(property)) {
			shouldBuy = true;
		} else if (this.gameStyle.getCompanyPurchase() && property.getDeedType().equals(DeedType.COMPANY)) {
			shouldBuy = true;
		} else if (this.deeds.size() % 3 == 0) {
			shouldBuy = true;
		}

		if (shouldBuy) {
			pay(property.getPurchaseValue());
			property.setIsPurchased(true);
			this.deeds.add(property);
			ConsolePrinter.println("Bot player " + color + " bought the property " + property.getName() + " for $" + property.getPurchaseValue() + "!");
			ConsolePrinter.println("Now, he has $" + moneyAvailable);
		} else {
			ConsolePrinter.println("Bot player " + color + " decided to not buy the property " + property.getName() + " for $" + property.getPurchaseValue() + "!");
		}

	}

	@Override
	public void addRanch(Province province) {
		if (province.getHasRanch()) {
			ConsolePrinter.println("Esta provincia ya tiene una estancia!");
		}

		int farmsProv = province.getFarmQuantity();
		int farmValue = province.getConstructionValue();
		int totalValue = farmValue;
		if (farmsProv == MAX_FARM_QUANTITY) {
			totalValue += farmValue;
		}
		totalValue += farmValue * (MAX_FARM_QUANTITY - farmsProv);
		if (getMoneyAvailable() * 0.5 >= totalValue || moreThan75Sell()) {
			pay(totalValue);
			province.setHasRanch(true);
		}
	}

	public boolean moreThan75Sell() {
		int quantityPurchased = 0;
		int total = 0;
		for (AbstractPlayer player : game.getPlayers()) {
			for (AbstractDeed deed : player.getDeeds()) {
				if (deed.getIsPurchased()) {
					quantityPurchased++;
				}
				total++;
			}
		}
		return total * 0.75 < quantityPurchased;
	}

	@Override
	public void addFarm(Province province, Integer quantity) {
		int totalValue = province.getConstructionValue() * quantity;
		if (quantity + province.getFarmQuantity() <= MAX_FARM_QUANTITY) {
			if (getMoneyAvailable() * 0.5 >= totalValue || moreThan75Sell()) {
				pay(totalValue);
				province.setFarmQuantity(province.getFarmQuantity() + quantity);
			}
		} else {
			ConsolePrinter.println("No se pueden agregar mas chacras!");
		}
	}

	@Override
	public void choosePropertyToMortgage() {
		AbstractDeed deedToMortage = null;
		int menor = 0;
		boolean primero = true;
		for (AbstractDeed deed : this.deeds) {
			if (!deed.getIsMortgaged()) {
				if (!this.gameStyle.getProvincesToPrioritize().contains(deed)) {
					deedToMortage = deed;
					break;
				} else if (deed.getMortgageValue() < menor || primero) {
					deedToMortage = deed;
					menor = deed.getMortgageValue();
					primero = false;
				}
			}
		}
		mortgageProperty(deedToMortage);
	}


	@Override
	public void mortgageProperty(AbstractDeed property) {
		if (this.deeds.contains(property)) {
			if (!property.getIsMortgaged()) {
				int mortgagedValue = property.getMortgageValue();
				property.setIsMortgaged(true);
				receive(mortgagedValue);
				ConsolePrinter.println("Property " + property.getName() + " has been mortgaged for $" + mortgagedValue + ".");
			} else {
				ConsolePrinter.println("Property  " + property.getName() + " is already mortgaged");
			}

		} else {
			ConsolePrinter.println("Property " + property.getName() + " is not owned by the player.");
		}
	}

}
