package ar.edu.utn.frc.tup.lciii.entities.deeds;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "COMPANIES")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
    private Integer idCompany;

    @ManyToOne
    @JoinColumn(name = "id_deed",nullable = false,foreignKey = @ForeignKey(name = "FK_COMPANIES__DEEDS"))
    private DeedEntity deed;

    @Column(name = "company_name", nullable = false, length = 50)
    private String companyName;

    @Column(name = "rent_1_company",nullable = false)
    private Integer rent1Company;

    @Column(name = "rent_2_companies",nullable = false)
    private Integer rent2Companies;

    @Column(name = "rent_3_companies",nullable = false)
    private Integer rent3Companies;
}