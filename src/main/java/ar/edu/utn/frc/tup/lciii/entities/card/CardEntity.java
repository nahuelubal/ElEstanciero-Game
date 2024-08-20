package ar.edu.utn.frc.tup.lciii.entities.card;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "CARDS")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_card")
    private Integer idCard;

    @ManyToOne
    @JoinColumn(name = "id_card_type", nullable = false, foreignKey = @ForeignKey(name = "FK_CARDS__CARD_TYPES"))
    private CardTypeEntity cardType;

    @Column(name = "is_destiny", nullable = false)
    private Boolean isDestiny;

    @Column(name = "card_description", nullable = false, length = 200)
    private String cardDescription;

    @Column(name = "card_value")
    private Integer cardValue;

    @ManyToOne
    @JoinColumn(name = "id_box", nullable = true, foreignKey = @ForeignKey(name = "FK_CARDS__BOXES"))
    private BoxEntity box;
}
