package ar.edu.utn.frc.tup.lciii.entities.config;

import ar.edu.utn.frc.tup.lciii.model.config.Difficulty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "DIFFICULTIES")
public class DifficultyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_difficulty")
    private Integer idDifficulty;

    @Column(name = "difficulty", nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    private Difficulty difficulty;
}
