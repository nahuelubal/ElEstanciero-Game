package ar.edu.utn.frc.tup.lciii.entities.config;

import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "TURNS")
public class TurnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turn")
    private Integer idTurn;
    @ManyToOne
    @JoinColumn(name = "id_player", nullable = false, foreignKey = @ForeignKey(name = "FK_TURNS__PLAYERS"))
    private PlayerEntity player;
}
