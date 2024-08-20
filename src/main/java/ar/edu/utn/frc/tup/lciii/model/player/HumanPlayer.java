package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.utils.InputValidator;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Data
@NoArgsConstructor
public class HumanPlayer extends AbstractPlayer {
	Scanner scanner = new Scanner(System.in);

	//Constructor
	public HumanPlayer(int idPlayer, GameStyle gameStyle, Color color, Integer playerOrder, int moneyAvailable, List<AbstractDeed> deeds, Boolean isActive, int idBox, boolean isPrisoner, int roundsStayQuantity, boolean hasHabeasCorpus) {
		super(idPlayer, gameStyle, color, playerOrder, moneyAvailable, deeds, isActive, idBox, isPrisoner, roundsStayQuantity, hasHabeasCorpus);
	}

	@Override
	public void choosePropertyToMortgage() {
		String index;
		List<AbstractDeed> availableDeeds = new ArrayList<>();
		boolean validInput = false;

		do {
			ConsolePrinter.println("Enter the number of the property you want to mortgage:");
			int i = 0;
			for (AbstractDeed deed : deeds) {
				if (!deed.getIsMortgaged()) {
					availableDeeds.add(deed);
					i++;
					ConsolePrinter.println(i + "-" + deed.getName() + " $" + deed.getMortgageValue());
				}
			}
			index = scanner.nextLine();

			if (InputValidator.isValidNumberInRange(index, 1, availableDeeds.size())) {
				validInput = true;
			} else {
				ConsolePrinter.println("Invalid input. Please enter a valid number.");
			}
		} while (!validInput);

		mortgageProperty(availableDeeds.get(Integer.parseInt(index) - 1));

	}

	@Override
	public void mortgageProperty(AbstractDeed property) {
		int mortgagedValue = property.getMortgageValue();
		property.setIsMortgaged(true);
		receive(mortgagedValue);
		ConsolePrinter.println("Property " + property.getName() + " has been mortgaged for $" + mortgagedValue + ".");
	}

	@Override
	public void addRanch(Province province) {
		if (province.getHasRanch()) {
			ConsolePrinter.println("This Province already has a ranch!");
			return;
		}

		int farmsProv = province.getFarmQuantity();
		int farmValue = province.getConstructionValue();
		int totalValue = farmValue;
		if (farmsProv == MAX_FARM_QUANTITY) {
			totalValue += farmValue;
		} else {
			totalValue += farmValue * (MAX_FARM_QUANTITY - farmsProv);
		}
		pay(totalValue);
		province.setHasRanch(true);

		//Ranch --> estancia
		//Farm --> Chacra

	}

	@Override
	public void addFarm(Province province, Integer quantity) {
		int totalValue = province.getConstructionValue() * quantity;
		if (quantity + province.getFarmQuantity() <= MAX_FARM_QUANTITY) {
			pay(totalValue);
			province.setFarmQuantity(province.getFarmQuantity() + quantity);
		} else {
			ConsolePrinter.println("You can´t add more farms.");
		}
	}

	@Override
	public void buyProperty(AbstractDeed property) {
		boolean wantsKeepPlaying = true;
		while (wantsKeepPlaying) {
			if (hasEnoughMoney(property.getPurchaseValue())) {
				pay(property.getPurchaseValue());
				deeds.add(property);
				property.setIsPurchased(true);
				wantsKeepPlaying = false;
			} else {
				ConsolePrinter.println("Insufficient money!");
				selectOption();
			}
		}
	}

	public void selectOption() {
		scanner.nextLine();
		boolean validOption = false;
		while (!validOption) {
			ConsolePrinter.println("You don't have enough money. Choose an option:");
			ConsolePrinter.println("1. Mortgage a property");
			ConsolePrinter.println("2. Back");
			String choice = scanner.nextLine();

			if (InputValidator.isValidNumberInRange(choice, 1, 2)) {
				switch (choice) {
					case "1":
						validOption = true;
						choosePropertyToMortgage();
						break;
					case "2":
						return;
				}
			} else {
				ConsolePrinter.println("Invalid option. Please choose 1 or 2.");
			}
		}
	}

}
