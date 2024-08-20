package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameStyleEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.*;
import ar.edu.utn.frc.tup.lciii.repository.player.PlayerRepository;
import ar.edu.utn.frc.tup.lciii.services.DeedService;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import org.modelmapper.ModelMapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class PlayerServiceImpl implements PlayerService {
	
	private final PlayerRepository playerRepository;
	private final DeedService deedService;
	private final ModelMapper modelMapper;

	private PlayerServiceImpl(PlayerRepository playerRepository, DeedService deedService, ModelMapper modelMapper) {
		this.playerRepository = playerRepository;
		this.deedService = deedService;
		this.modelMapper = modelMapper;
	}

	private static class Holder {
		private static PlayerServiceImpl INSTANCE;
	}

	public static PlayerServiceImpl getInstance(PlayerRepository playerRepository, DeedService deedService, ModelMapper modelMapper) {
		if (PlayerServiceImpl.Holder.INSTANCE == null) {
			PlayerServiceImpl.Holder.INSTANCE = new PlayerServiceImpl(playerRepository, deedService, modelMapper);
		}

		return PlayerServiceImpl.Holder.INSTANCE;
	}

	@Override
	public Queue<AbstractPlayer> getAllPlayersByGameId(int gameId) {
		List<PlayerEntity> playerEntities = playerRepository.getPlayersByGameId(gameId);
		Queue<AbstractPlayer> playerList = new LinkedList<>();

		for (PlayerEntity playerEntity : playerEntities) {
			if (Objects.nonNull(playerEntity.getGameStyle())) {
				if (playerEntity.getGameStyle().getGameStyle().equals(BotPlayerTypes.CONSERVATIVE)) {
					ConservativeBotPlayer conservativeBotPlayer = modelMapper.map(playerEntity, ConservativeBotPlayer.class);
					String color = playerEntity.getColor().getColor().toUpperCase();
					conservativeBotPlayer.setColor(Color.valueOf(color));
					conservativeBotPlayer.setIdBox(playerEntity.getBox().getIdBox());
					List<AbstractDeed> deeds = deedService.getDeedListByPlayerId(playerEntity.getIdPlayer());
					GameStyle conservativeGameStyle = this.getGameStyleByBotType(BotPlayerTypes.CONSERVATIVE);
					conservativeBotPlayer.setDeeds(deeds);
					conservativeBotPlayer.setGameStyle(conservativeGameStyle);
					playerList.add(conservativeBotPlayer);
				} else if (playerEntity.getGameStyle().getGameStyle().equals(BotPlayerTypes.WELL_BALANCED)) {
					WellBalancedBotPlayer wellBalancedBotPlayer = modelMapper.map(playerEntity, WellBalancedBotPlayer.class);
					String color = playerEntity.getColor().getColor().toUpperCase();
					wellBalancedBotPlayer.setColor(Color.valueOf(color));
					wellBalancedBotPlayer.setIdBox(playerEntity.getBox().getIdBox());
					List<AbstractDeed> deeds = deedService.getDeedListByPlayerId(playerEntity.getIdPlayer());
					GameStyle wellBalancedGameStyle = this.getGameStyleByBotType(BotPlayerTypes.WELL_BALANCED);
					wellBalancedBotPlayer.setDeeds(deeds);
					wellBalancedBotPlayer.setGameStyle(wellBalancedGameStyle);
					playerList.add(wellBalancedBotPlayer);
				} else {
					AggressiveBotPlayer aggressiveBotPlayer = modelMapper.map(playerEntity, AggressiveBotPlayer.class);
					String color = playerEntity.getColor().getColor().toUpperCase();
					aggressiveBotPlayer.setColor(Color.valueOf(color));
					aggressiveBotPlayer.setIdBox(playerEntity.getBox().getIdBox());
					List<AbstractDeed> deeds = deedService.getDeedListByPlayerId(playerEntity.getIdPlayer());
					GameStyle aggressiveGameStyle = this.getGameStyleByBotType(BotPlayerTypes.AGGRESSIVE);
					aggressiveBotPlayer.setDeeds(deeds);
					aggressiveBotPlayer.setGameStyle(aggressiveGameStyle);
					playerList.add(aggressiveBotPlayer);
				}
			} else {
				HumanPlayer humanPlayer = modelMapper.map(playerEntity, HumanPlayer.class);
				String color = playerEntity.getColor().getColor().toUpperCase();
				humanPlayer.setColor(Color.valueOf(color));
				humanPlayer.setIdBox(playerEntity.getBox().getIdBox());
				List<AbstractDeed> deeds = deedService.getDeedListByPlayerId(playerEntity.getIdPlayer());
				humanPlayer.setDeeds(deeds);
				playerList.add(humanPlayer);
			}
		}

		return playerList;
	}

	@Override
	public void saveOrUpdatePlayer(AbstractPlayer player) {
		PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
		playerRepository.saveOrUpdatePlayer(playerEntity);
	}

	@Override
	public GameStyleEntity getGameStyleByName(GameStyle gameStyle) {
		return playerRepository.getGameStyleByName(gameStyle);
	}

	@Override
	public GameStyle getGameStyleByBotType(BotPlayerTypes botPlayerType) {
		GameStyle gameStyle = modelMapper.map(playerRepository.getGameStyleByBotType(botPlayerType), GameStyle.class);
		List<Province> provinceList = deedService.getProvincesByStyleGame(gameStyle);
		gameStyle.setProvincesToPrioritize(provinceList);
		return gameStyle;
	}

	@Override
	public BoxEntity getBoxById(int boxId) {
		return playerRepository.getBoxById(boxId);
	}
}
