package ar.edu.utn.frc.tup.lciii.model.deeds;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Railway extends AbstractDeed {
	private int idRailway;
	private String railwayName;
	private int rent_1_railway;
	private int rent_2_railway;
	private int rent_3_railway;
	private int rent_4_railway;


	//Constructor
	public Railway(int idDeed, String name, int purchaseValue, int mortgageValue, boolean isPurchased,
				   boolean isMortgaged, int idRailway, String railwayName, int rent_1, int rent_2, int rent_3, int rent_4, DeedType deedType) {
		super(idDeed, name, purchaseValue, mortgageValue, isPurchased, isMortgaged, deedType);
		this.idRailway = idRailway;
		this.railwayName = railwayName;
		this.rent_1_railway = rent_1;
		this.rent_2_railway = rent_2;
		this.rent_3_railway = rent_3;
		this.rent_4_railway = rent_4;
	}

	public int getRent(int quantityRailways) {
		int rent = switch (quantityRailways) {
			case 1 -> rent_1_railway;
			case 2 -> rent_2_railway;
			case 3 -> rent_3_railway;
			case 4 -> rent_4_railway;
			default -> 0;
		};
		return rent;
	}
}
