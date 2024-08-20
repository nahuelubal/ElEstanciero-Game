package ar.edu.utn.frc.tup.lciii.entities.config;

import ar.edu.utn.frc.tup.lciii.model.player.BotPlayerTypes;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "GAMES_STYLES")
public class GameStyleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_game_style")
    private Integer idGameStyle;

    @Column(name = "game_style", nullable = false, length = 64)
    @Enumerated(EnumType.STRING)
    private BotPlayerTypes gameStyle;

    @Column(name = "railway_purchase", nullable = false)
    private Boolean railwayPurchase;

    @Column(name = "company_purchase", nullable = false)
    private Boolean companyPurchase;

    @Column(name = "purchase_possibility", nullable = false)
    private Integer purchasePossibility;
}
