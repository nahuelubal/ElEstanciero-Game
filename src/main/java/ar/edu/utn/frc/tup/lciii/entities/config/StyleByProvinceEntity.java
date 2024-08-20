package ar.edu.utn.frc.tup.lciii.entities.config;

import ar.edu.utn.frc.tup.lciii.entities.deeds.ProvinceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "STYLES_BY_PROVINCES")
public class StyleByProvinceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_style_by_province")
    private Integer idStyleByProvince;

    @ManyToOne
    @JoinColumn(name = "id_game_style", nullable = false, foreignKey = @ForeignKey(name = "FK_STYLES_BY_PROVINCES__STYLES"))
    private GameStyleEntity idGameStyle;

    @ManyToOne
    @JoinColumn(name = "id_province", nullable = false, foreignKey = @ForeignKey(name = "FK_STYLES_BY_PROVINCES__PROVINCES"))
    private ProvinceEntity idProvince;
}
