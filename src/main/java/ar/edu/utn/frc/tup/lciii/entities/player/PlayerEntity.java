package ar.edu.utn.frc.tup.lciii.entities.player;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.ColorEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameStyleEntity;
import ar.edu.utn.frc.tup.lciii.entities.user.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "PLAYERS")
public class PlayerEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_player")
	private Integer idPlayer;

	@ManyToOne
	@JoinColumn(name = "id_user", foreignKey = @ForeignKey(name = "FK_PLAYERS__USERS"))
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "id_game", nullable = false, foreignKey = @ForeignKey(name = "FK_PLAYERS__GAMES"))
	private GameEntity game;

	@ManyToOne
	@JoinColumn(name = "id_game_style",nullable = true, foreignKey = @ForeignKey(name = "FK_PLAYERS__GAMES_STYLES"))
	private GameStyleEntity gameStyle;

	@ManyToOne
	@JoinColumn(name = "id_color", nullable = false, foreignKey = @ForeignKey(name = "FK_PLAYERS__COLORS"))
	private ColorEntity color;

	@ManyToOne
	@JoinColumn(name = "id_box", nullable = false, foreignKey = @ForeignKey(name = "FK_PLAYERS__BOXES"))
	private BoxEntity box;

	@Column(name = "player_order", nullable = false)
	private Integer playerOrder;

	@Column(name = "available_money", nullable = false)
	private Integer availableMoney;

	@Column(name = "is_active", nullable = false)
	private Boolean isActive;

}
