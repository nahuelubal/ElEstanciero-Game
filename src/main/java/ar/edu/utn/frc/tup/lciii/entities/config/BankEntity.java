package ar.edu.utn.frc.tup.lciii.entities.config;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "BANKS")
public class BankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bank")
    private Integer idBank;

    @ManyToOne
    @JoinColumn(name = "id_game", nullable = false, foreignKey = @ForeignKey(name = "FK_BANKS_GAMES"))
    private GameEntity idGame;

    @Column(name = "available_money")
    private Integer availableMoney;
}
