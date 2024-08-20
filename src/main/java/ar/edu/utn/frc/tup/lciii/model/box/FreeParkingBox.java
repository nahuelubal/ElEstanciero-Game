package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FreeParkingBox extends AbstractBox {
	@Override
	public void actionBox(AbstractPlayer player) {
		ConsolePrinter.println("Free Parking!");
		ConsolePrinter.println("Wait for the next turn...");
	}
}
