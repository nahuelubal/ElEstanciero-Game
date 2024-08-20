package ar.edu.utn.frc.tup.lciii.entities.card;

import ar.edu.utn.frc.tup.lciii.model.card.CardType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CARD_TYPES")
public class CardTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card_type")
    private Integer idCardType;

    @Column(name = "card_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private CardType cardTypeName;
}
