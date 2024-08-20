package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.others.console_writer.ConsolePrinter;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class GoToJailBox extends AbstractBox {

	public GoToJailBox(Integer idBox, Integer boxNumber, List<AbstractPlayer> players, BoxType boxType) {
		super(idBox, boxNumber, players, boxType);
	}

	@Override
	public void actionBox(AbstractPlayer player) {
		ConsolePrinter.println("Go To Jail!");

		Dashboard dashboard = Dashboard.getInstance();
		try {
			JailBox jailBox = (JailBox) dashboard.findBoxByType(JailBox.class);
			player.setPrisoner(true);
			if (jailBox == null) {
				throw new NullPointerException("No se encontró la casilla Carcel.");
			}

			dashboard.movePlayerByBox(player, jailBox.getIdBox());
		} catch (NullPointerException e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
