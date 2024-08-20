package ar.edu.utn.frc.tup.lciii.entities.deeds;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "DEEDS_BY_PLAYERS")
public class DeedByPlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deed_by_player")
    private Integer idDeedByPlayer;

    @ManyToOne
    @JoinColumn(name = "id_deed",nullable = false,foreignKey = @ForeignKey(name = "FK_DEEDS_BY_PLAYERS__DEEDS"))
    private DeedEntity deed;

    @ManyToOne
    @JoinColumn(name = "id_player",nullable = false,foreignKey = @ForeignKey(name = "FK_DEEDS_BY_PLAYERS__PLAYERS"))
    private PlayerEntity player;

    @Column(name = "is_purchased",nullable = false)
    private Boolean isPurchased;

    @Column(name = "is_mortgaged",nullable = false)
    private Boolean isMortgaged;

    @Column(name = "farm_quantity",nullable = false)
    private Integer farmQuantity;

    @Column(name = "has_ranch",nullable = false)
    private Boolean hasRanch;

}
