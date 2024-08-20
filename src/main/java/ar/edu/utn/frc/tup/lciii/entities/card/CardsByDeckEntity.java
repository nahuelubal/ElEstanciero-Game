package ar.edu.utn.frc.tup.lciii.entities.card;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CARDS_BY_DECKS")
public class CardsByDeckEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card_by_deck")
    private Integer idCardByDeck;

    @ManyToOne
    @JoinColumn(name = "id_deck_of_card", nullable = false, foreignKey = @ForeignKey(name = "FK_CARDS_BY_DECKS__DECKS_OF_CARDS"))
    private DeckOfCardEntity deckOfCard;

    @ManyToOne
    @JoinColumn(name = "id_card", nullable = false, foreignKey = @ForeignKey(name = "FK_CARDS_BY_DECKS__CARDS"))
    private CardEntity card;
}
