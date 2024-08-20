package ar.edu.utn.frc.tup.lciii.model.box;

import ar.edu.utn.frc.tup.lciii.interfaces.box.IBox;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AbstractBox implements IBox {

	//Atributes
	private Integer idBox;
	private Integer boxNumber;
	private List<AbstractPlayer> players;
	private BoxType boxType;
	private String description;

	//Constructor
	public AbstractBox(Integer idBox, Integer boxNumber, List<AbstractPlayer> players, BoxType boxType) {
		this.idBox = idBox;
		this.boxNumber = boxNumber;
		this.players = new ArrayList<>(players);
		this.boxType = boxType;
	}

	public AbstractBox() {
		this.idBox = 0;
		this.boxNumber = 0;
		this.players = new ArrayList<AbstractPlayer>();
	}

	public String getDescription() {
		if (description != null) {
			return description;
		} else {
			return "Without description";
		}
	}

	//Methods
	public void addPlayerToBox(AbstractPlayer player) {
		players.add(player);
	}

	public void removePlayer(AbstractPlayer player) {
		players.remove(player);
	}
}
