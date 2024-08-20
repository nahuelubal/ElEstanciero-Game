package ar.edu.utn.frc.tup.lciii.entities.card;

import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "DECKS_OF_CARDS")
public class DeckOfCardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deck_of_card")
    private Integer idDeckOfCard;

    @ManyToOne
    @JoinColumn(name = "id_game", nullable = false, foreignKey = @ForeignKey(name = "FK_DECKS_OF_CARDS__GAMES"))
    private GameEntity game;
}
