package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.interfaces.card.ICard;
import lombok.*;

@Data
public abstract class AbstractCard implements ICard {
	private int idCard;
	private String description;
	private Integer value;
	private Boolean isDestiny;
	private Integer boxId;

	public AbstractCard() {
		this.isDestiny = false;
		this.value = 100;
		this.description = "Default Card";
		this.boxId = 0;
	}

	public AbstractCard(int idCard, String description, Integer value, Boolean isDestiny, Integer boxId) {
		this.idCard = idCard;
		this.description = description;
		this.value = value;
		this.isDestiny = isDestiny;
		this.boxId = boxId;
	}
}
