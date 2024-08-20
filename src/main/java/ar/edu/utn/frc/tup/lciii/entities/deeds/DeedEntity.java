package ar.edu.utn.frc.tup.lciii.entities.deeds;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.model.deeds.DeedType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "DEEDS")
public class DeedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deed")
    private Integer idDeed;

    @ManyToOne
    @JoinColumn(name = "id_box", nullable = false, foreignKey = @ForeignKey(name = "FK_DEEDS__BOXES"))
    private BoxEntity box;

    @Column(name = "deed_name", nullable = false, length = 50)
    private String deedName;

    @Column(name = "purchase_value", nullable = false)
    private Integer purchaseValue;

    @Column(name = "mortgage_value", nullable = false)
    private Integer mortgageValue;

    @Column(name = "deed_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DeedType deedType;
}
