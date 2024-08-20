package ar.edu.utn.frc.tup.lciii.entities.deeds;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "RAILWAYS")
public class RailwayEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_railway")
    private Integer idRailway;

    @ManyToOne
    @JoinColumn(name = "id_deed", nullable = false)
    private DeedEntity deed;

    @Column(name = "railway_name", nullable = false, length = 50)
    private String railwayName;

    @Column(name = "rent_1_railway", nullable = false)
    private int rent1Railway;

    @Column(name = "rent_2_railways", nullable = false)
    private int rent2Railways;

    @Column(name = "rent_3_railways", nullable = false)
    private int rent3Railways;

    @Column(name = "rent_4_railways", nullable = false)
    private int rent4Railways;
}