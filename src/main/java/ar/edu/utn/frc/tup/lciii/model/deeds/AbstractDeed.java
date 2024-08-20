package ar.edu.utn.frc.tup.lciii.model.deeds;

import ar.edu.utn.frc.tup.lciii.interfaces.deeds.IDeed;
import lombok.Data;

@Data
public abstract class AbstractDeed implements IDeed {
	//Atributes
	private int idDeed;
	private String name;
	private int purchaseValue;
	private int mortgageValue;
	private Boolean isPurchased;
	private Boolean isMortgaged;
	private DeedType deedType;

	//Constructor
	public AbstractDeed(int idDeed, String name, int purchaseValue, int mortgageValue, Boolean isPurchased, Boolean isMortgaged, DeedType DeedType) {
		this.idDeed = idDeed;
		this.name = name;
		this.purchaseValue = purchaseValue;
		this.mortgageValue = mortgageValue;
		this.isPurchased = isPurchased;
		this.isMortgaged = isMortgaged;
		this.deedType = DeedType;
	}

	public AbstractDeed() {
		this.isMortgaged = false;
		this.isPurchased = false;
	}
}
