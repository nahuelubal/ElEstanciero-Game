package ar.edu.utn.frc.tup.lciii.model.deeds;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company extends AbstractDeed {
	private int idCompany;
	private String companyName;
	private int rent_1_company;
	private int rent_2_company;
	private int rent_3_company;

	public Company(int idDeed, String name, int purchaseValue, int mortgageValue, boolean isPurchased,
				   boolean isMortgaged, int idCompany, String companyName, int rent1, int rent2, int rent3, DeedType deedType) {
		super(idDeed, name, purchaseValue, mortgageValue, isPurchased, isMortgaged, deedType);
		this.idCompany = idCompany;
		this.companyName = companyName;
		this.rent_1_company = rent1;
		this.rent_2_company = rent2;
		this.rent_3_company = rent3;
	}

	public Integer getRent(int numberOfCompanies, int numberDices) {
		return switch (numberOfCompanies) {
			case 1 -> numberDices * rent_1_company;
			case 2 -> numberDices * rent_2_company;
			case 3 -> numberDices * rent_3_company;
			default -> 0;
		};
	}

}
