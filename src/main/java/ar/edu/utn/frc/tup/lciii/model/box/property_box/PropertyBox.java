package ar.edu.utn.frc.tup.lciii.model.box.property_box;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public abstract class PropertyBox extends AbstractBox {
	AbstractDeed deed;

	public void showRentText(Color ownerColor, Color playerColor, int rentValue, AbstractDeed deed, int playerMoneyAvailable) {
		ConsolePrinter.println("The player " + ownerColor + " has in his properties: " + deed.getName());
		ConsolePrinter.println("The rent is: $" + rentValue);
		ConsolePrinter.println("Your available money is: $" + playerMoneyAvailable);
		ConsolePrinter.println("Player " + playerColor + " must pay $" + rentValue);
	}

	public void showBuyText(AbstractDeed deed) {
		ConsolePrinter.println("The player does not have in his possession: " + deed.getName());
		ConsolePrinter.println("Do you want to buy it for $" + deed.getPurchaseValue() + "?");
		ConsolePrinter.println("Select Y (Yes) / N (No):");
	}

	public void showBoughtText(AbstractDeed deed, int playerMoneyAvailable) {
		ConsolePrinter.println("The player bought the property " + deed.getName() + "!");
		ConsolePrinter.println("Now, the player has $" + playerMoneyAvailable);
	}

}