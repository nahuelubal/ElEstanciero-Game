package ar.edu.utn.frc.tup.lciii.entities.config;

import ar.edu.utn.frc.tup.lciii.entities.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "GAMES")
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game")
    private Integer idGame;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false, foreignKey = @ForeignKey(name = "FK_GAMES__USERS"))
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "id_difficulty", nullable = false, foreignKey = @ForeignKey(name = "FK_GAMES__DIFFICULTIES"))
    private DifficultyEntity difficulty;

    @Column(name = "round_number")
    private Integer roundNumber;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}