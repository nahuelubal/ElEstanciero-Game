package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class StartBox extends AbstractBox {
	private int amount;

	public StartBox(Integer idBox, Integer boxNumber, List<AbstractPlayer> players, BoxType boxType) {
		super(idBox, boxNumber, players, boxType);
	}

	@Override
	public void actionBox(AbstractPlayer player) {

	}


}
