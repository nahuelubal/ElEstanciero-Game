package ar.edu.utn.frc.tup.lciii.entities.box;

import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "BOX_TYPES")
public class BoxTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_box_type")
    private Integer idBoxType;

    @Column(name = "box_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private BoxType boxTypeName;
}
