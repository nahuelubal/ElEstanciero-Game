package ar.edu.utn.frc.tup.lciii.dtos.game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedGame {
    private Integer idGame;
    private LocalDateTime updatedAt;
}
