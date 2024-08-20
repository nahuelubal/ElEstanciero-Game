package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.DeedType;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class ConservativeBotPlayer extends BotPlayer {

	@Override
	public void buyProperty(AbstractDeed property) {

		boolean shouldBuy = false;

		if (gameStyle.getProvincesToPrioritize().contains(property)) {
			shouldBuy = true;
		} else if (this.gameStyle.getCompanyPurchase() && property.getDeedType().equals(DeedType.COMPANY)) {
			shouldBuy = true;
		} else if (this.deeds.size() % 5 == 0) {
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
			ConsolePrinter.println("This Province already has a ranch!");
		}
		int farmsProv = province.getFarmQuantity();
		int farmValue = province.getConstructionValue();
		int totalValue = farmValue;
		if (farmsProv == MAX_FARM_QUANTITY) {
			totalValue += farmValue;
		} else {
			totalValue += farmValue * (MAX_FARM_QUANTITY - farmsProv);
		}
		if (getMoneyAvailable() * 0.3 >= totalValue) {
			pay(totalValue);
			province.setHasRanch(true);
		}

	}

	@Override
	public void addFarm(Province province, Integer quantity) {
		int totalValue = province.getConstructionValue() * quantity;
		if (quantity + province.getFarmQuantity() <= MAX_FARM_QUANTITY) {
			if (getMoneyAvailable() * 0.3 >= totalValue) {
				pay(totalValue);
				province.setFarmQuantity(province.getFarmQuantity() + quantity);
			}
		} else {
			ConsolePrinter.println("You can´t add more farms.");
		}
	}


	@Override
	public void choosePropertyToMortgage() {
		AbstractDeed deedToMortage = null;
		int menor = 0;
		boolean primero = true;

		for (AbstractDeed deed : this.deeds) {
			if (!gameStyle.getProvincesToPrioritize().contains(deed)) {
				deedToMortage = deed;
				break;
			} else if (deed.getMortgageValue() < menor || primero) {
				deedToMortage = deed;
				menor = deed.getMortgageValue();
				primero = false;
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
