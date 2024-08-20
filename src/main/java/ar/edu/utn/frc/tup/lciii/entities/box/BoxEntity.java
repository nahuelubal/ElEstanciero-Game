package ar.edu.utn.frc.tup.lciii.entities.box;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "BOXES")
public class BoxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_box")
    private Integer idBox;

    @ManyToOne
    @JoinColumn(name = "id_box_type", nullable = false, foreignKey = @ForeignKey(name = "FK_BOXES__BOX_TYPES"))
    private BoxTypeEntity boxType;

    @Column(name = "description", nullable = false)
    private String description;
}
