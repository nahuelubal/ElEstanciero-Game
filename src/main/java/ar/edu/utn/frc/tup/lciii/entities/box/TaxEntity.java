package ar.edu.utn.frc.tup.lciii.entities.box;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TAXES")
public class TaxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tax")
    private Integer idTax;

    @ManyToOne
    @JoinColumn(name = "id_box", nullable = false, foreignKey = @ForeignKey(name = "FK_TAXES__BOXES"))
    private BoxEntity idBox;

    @Column(name = "tax_description", nullable = false, length = 50)
    private String taxDescription;

    @Column(name = "amount", nullable = false)
    private Integer amount;
}
