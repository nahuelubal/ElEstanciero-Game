package ar.edu.utn.frc.tup.lciii.model.deeds;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Province extends AbstractDeed {
	private int idProvince;
	private int farmQuantity;
	private Boolean hasRanch;
	private Zone zone;
	private int constructionValue;
	private int rentValue;
	private int rent_1_farm;
	private int rent_2_farm;
	private int rent_3_farm;
	private int rent_4_farm;
	private int rent_ranch;

	//Constructor
	public Province(int idDeed, String name, int purchaseValue, int mortgageValue, Boolean isPurchased,
					Boolean isMortgaged, int idProvince, int farmQuantity, Boolean hasRanch, Zone zone,
					int constructionValue, int rentValue, int rent1Farm, int rent2Farm, int rent3Farm, int rent4Farm, int rentRanch, DeedType deedType) {
		super(idDeed, name, purchaseValue, mortgageValue, isPurchased, isMortgaged, deedType);
		this.idProvince = idProvince;
		this.farmQuantity = farmQuantity;
		this.hasRanch = hasRanch;
		this.zone = zone;
		this.constructionValue = constructionValue;
		this.rentValue = rentValue;
		this.rent_1_farm = rent1Farm;
		this.rent_2_farm = rent2Farm;
		this.rent_3_farm = rent3Farm;
		this.rent_4_farm = rent4Farm;
		this.rent_ranch = rentRanch;
	}

	public Province() {
		this.hasRanch = false;
		this.farmQuantity = 0;
	}

	public Integer getRent(boolean hasProvinceEntire) {
		int rent;
		if (!hasProvinceEntire) {
			rent = this.rentValue;
		} else {
			if (hasRanch) {
				rent = rent_ranch;
			} else if (farmQuantity > 0 && farmQuantity <= 4) {
				rent = switch (farmQuantity) {
					case 1 -> rent_1_farm;
					case 2 -> rent_2_farm;
					case 3 -> rent_3_farm;
					case 4 -> rent_4_farm;
					default -> this.rentValue;
				};
			} else {
				rent = this.rentValue * 2;
			}
			return rent;
		}
		return rent;
	}
}
