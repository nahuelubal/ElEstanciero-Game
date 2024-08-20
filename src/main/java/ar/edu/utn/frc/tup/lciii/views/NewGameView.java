package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.console_writer.Align;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleColor;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsoleConfig;
import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.session.Session;
import lombok.Data;

@Data
public class NewGameView extends AbstractView {

	private ChooseDifficultyView chooseDifficultyView;

	public NewGameView() {
		super();
		this.chooseDifficultyView = new ChooseDifficultyView();
	}

	@Override
	public void render() {
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		ConsolePrinter.println("Let's create a new game!", new ConsoleConfig(Align.CENTER, true));
		ConsolePrinter.printlnColor(ConsolePrinter.buildLine('-'), ConsoleColor.BLUE, new ConsoleConfig(Align.CENTER));
		Game.getInstance().cleanInstance();
		Game.getInstance().setUser(Session.getUser());
		chooseDifficultyView.render();
	}
}
