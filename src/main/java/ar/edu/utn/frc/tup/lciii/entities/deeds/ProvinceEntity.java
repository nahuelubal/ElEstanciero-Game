package ar.edu.utn.frc.tup.lciii.entities.deeds;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "PROVINCES")
public class ProvinceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_province")
    private Integer idProvince;

    @ManyToOne
    @JoinColumn(name = "id_deed", nullable = false)
    private DeedEntity deed;

    @ManyToOne
    @JoinColumn(name = "id_zone", nullable = false)
    private ZoneEntity zone;

    @Column(name = "province_name", nullable = false,length = 50)
    private String provinceName;

    @Column(name = "construction_value", nullable = false)
    private Integer constructionValue;

    @Column(name = "rent", nullable = false)
    private Integer rent;

    @Column(name = "rent_1_farm", nullable = false)
    private Integer rent1Farm;

    @Column(name = "rent_2_farms", nullable = false)
    private Integer rent2Farms;

    @Column(name = "rent_3_farms", nullable = false)
    private Integer rent3Farms;

    @Column(name = "rent_4_farms", nullable = false)
    private Integer rent4Farms;

    @Column(name = "rent_ranch", nullable = false)
    private Integer rentRanch;
}