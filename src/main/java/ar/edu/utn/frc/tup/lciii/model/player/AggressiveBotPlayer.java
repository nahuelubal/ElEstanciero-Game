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

import java.util.Scanner;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggressiveBotPlayer extends BotPlayer {


	private Game game = Game.getInstance();
	Scanner scanner = new Scanner(System.in);

	@Override
	public void buyProperty(AbstractDeed property) {
		boolean shouldBuy = false;

		if (gameStyle.getRailwayPurchase() && property.getDeedType().equals(DeedType.RAILWAY)) {
			shouldBuy = true;
		} else if (gameStyle.getProvincesToPrioritize().contains(property)) {
			shouldBuy = true;
		} else if (this.gameStyle.getCompanyPurchase() && property.getDeedType().equals(DeedType.COMPANY)) {
			shouldBuy = true;
		} else if (lostAProperty() || boughtAllPriorityProperties()) {
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

	/**
	 * @return TRUE , si algun jugador que no sea el bot agresivo compro una de las
	 * propiedades de prioridad del bot agresivo.
	 * sirve para que el bot tome la decision de comprar cualquier provincia
	 * (si compraron una de sus provincias prioritarias)
	 */
	private boolean lostAProperty() {
		boolean flag = false;
		for (AbstractPlayer player : game.getPlayers()) {
			if (!player.equals(this)) {
				for (AbstractDeed deed : player.getDeeds()) {
					if (this.gameStyle.getProvincesToPrioritize().contains(deed)) {
						flag = true;
						break;
					}
				}
			}
		}

		return flag;
	}

	/**
	 * @return TRUE
	 * si el bot agresivo compro todas sus propiedades prioritarias
	 */
	private boolean boughtAllPriorityProperties() {
		Integer contador = 0;
		for (Province province : this.gameStyle.getProvincesToPrioritize()) {
			for (AbstractDeed deed : this.deeds) {
				if (deed.equals(province)) {
					contador++;
				}
			}
		}

		return contador.equals(this.gameStyle.getProvincesToPrioritize().size());
	}

	@Override
	public void addFarm(Province province, Integer quantity) {
		int totalCost = province.getConstructionValue() * quantity;

		if (this.deeds.contains(province)) {
			if (quantity + province.getFarmQuantity() <= MAX_FARM_QUANTITY) {
				pay(totalCost);
				province.setFarmQuantity(province.getFarmQuantity() + quantity);
				ConsolePrinter.println("Added " + quantity + " farm(s) to " + province.getName() + ".");
			} else {
				ConsolePrinter.println("You can´t add more farms.");
			}
		} else {
			ConsolePrinter.println("This province doesn´t belong you");
		}
	}

	@Override
	public void addRanch(Province province) {
		if (!province.getHasRanch()) {
			pay(calculateRanchValue(province));
			province.setHasRanch(true);
		} else {
			ConsolePrinter.println("This province already has ranch");
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

	public void mortgageProperty(AbstractDeed property) {
		int mortgagedValue = property.getMortgageValue();
		property.setIsMortgaged(true);
		receive(mortgagedValue);
		ConsolePrinter.println("Property " + property.getName() + " has been mortgaged for $" + mortgagedValue + ".");
	}

}



