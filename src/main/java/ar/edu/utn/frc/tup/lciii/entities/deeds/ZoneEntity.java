package ar.edu.utn.frc.tup.lciii.entities.deeds;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "ZONES")
public class ZoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_zone")
    private Integer id;

    @Column(name = "zone", nullable = false, length = 50)
    private String zone;

}
