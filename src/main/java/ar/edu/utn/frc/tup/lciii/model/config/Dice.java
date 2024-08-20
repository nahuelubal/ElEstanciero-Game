package ar.edu.utn.frc.tup.lciii.model.config;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import lombok.Data;

import java.util.Random;

@Data
public class Dice {

	private int dice1;
	private int dice2;
	private int addition;

	public Integer rollDices() {
		Random rand = new Random();

		dice1 = rand.nextInt(1, 7);
		dice2 = rand.nextInt(1, 7);

		ConsolePrinter.println(
				String.format("The result of the rolls is %d and %d", dice1, dice2)
		);

		addition = dice1 + dice2;

		return addition;
	}

	public Boolean areDicesEquals() {
		if (dice1 == dice2) {
			ConsolePrinter.println("The result of your rolls are equals, you can roll again");
			return true;
		} else {
			return false;
		}
	}
}
