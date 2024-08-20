package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.game.SavedGame;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.ColorEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.DifficultyEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.DeedByPlayerEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.DeedEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entities.user.UserEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.game.IGameRepository;
import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.config.*;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.*;
import ar.edu.utn.frc.tup.lciii.model.session.Session;
import ar.edu.utn.frc.tup.lciii.services.BoxService;
import ar.edu.utn.frc.tup.lciii.services.DeedService;
import ar.edu.utn.frc.tup.lciii.services.GameService;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.*;

public class GameServiceImpl implements GameService {
    private static final Integer START_AMOUNT = 35000;

    private final IGameRepository gameRepository;
    private final PlayerService playerService;
    private final DeedService deedService;
    private final BoxService boxService;
    private final ModelMapper modelMapper;

    private GameServiceImpl(IGameRepository gameRepository, PlayerService playerService, DeedService deedService, BoxService boxService, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
        this.deedService = deedService;
        this.boxService = boxService;
        this.modelMapper = modelMapper;
    }

    private static class Holder {
        private static GameServiceImpl INSTANCE;
    }

    public static GameServiceImpl getInstance(IGameRepository gameRepository, PlayerService playerService, DeedService deedService, BoxService boxService, ModelMapper modelMapper) {
        if (Holder.INSTANCE == null) {
            Holder.INSTANCE = new GameServiceImpl(gameRepository, playerService, deedService, boxService, modelMapper);
        }
        return Holder.INSTANCE;
    }

    @Override
    public Queue<AbstractPlayer> createPlayers(Difficulty difficulty) {
        List<AbstractPlayer> players = new ArrayList<>();

        GameStyle conservativeGameStyle = playerService.getGameStyleByBotType(BotPlayerTypes.CONSERVATIVE);
        GameStyle aggressiveGameStyle = playerService.getGameStyleByBotType(BotPlayerTypes.AGGRESSIVE);
        GameStyle wellBalancedGameStyle = playerService.getGameStyleByBotType(BotPlayerTypes.WELL_BALANCED);

        ConservativeBotPlayer conservativeBotPlayer = new ConservativeBotPlayer();
        conservativeBotPlayer.setGameStyle(conservativeGameStyle);

        WellBalancedBotPlayer wellBalancedPlayer = new WellBalancedBotPlayer();
        wellBalancedPlayer.setGameStyle(wellBalancedGameStyle);

        WellBalancedBotPlayer wellBalancedPlayer2 = new WellBalancedBotPlayer();
        wellBalancedPlayer2.setGameStyle(wellBalancedGameStyle);

        AggressiveBotPlayer aggressiveBotPlayer = new AggressiveBotPlayer();
        aggressiveBotPlayer.setGameStyle(aggressiveGameStyle);

        switch (difficulty) {
            case EASY -> {
                players.add(conservativeBotPlayer);
                players.add(wellBalancedPlayer);
            }
            case MEDIUM -> {
                players.add(conservativeBotPlayer);
                players.add(wellBalancedPlayer);
                players.add(aggressiveBotPlayer);
            }
            case HARD -> {
                players.add(conservativeBotPlayer);
                players.add(wellBalancedPlayer);
                players.add(wellBalancedPlayer2);
                players.add(aggressiveBotPlayer);
            }
        }

        List<Integer> indexColors = new ArrayList<>();

        for (AbstractPlayer p : players) {
            p.setMoneyAvailable(START_AMOUNT);
            p.setIsActive(true);
            p.setPrisoner(false);
            p.setColor(getRandomColor(indexColors));
        }

        return new LinkedList<>(players);
    }

    @Override
    public Queue<AbstractPlayer> getAllPlayersByGameId(int gameId) {
        return playerService.getAllPlayersByGameId(gameId);
    }

    @Override
    public Color getRandomColor(List<Integer> indexColors) {
        Random random = new Random();

        for (AbstractPlayer player : Game.getInstance().getPlayers()) {
            if (player instanceof HumanPlayer) {
                Color playerColor = player.getColor();
                Integer playerColorIndex = playerColor.ordinal();
                indexColors.add(playerColorIndex);
            }
            break;
        }

        Integer randomIndex;
        do {
            int bound = Color.values().length - 1;
            randomIndex = random.nextInt(0, bound);
        } while (indexColors.contains(randomIndex));

        indexColors.add(randomIndex);
        return Color.values()[randomIndex];
    }

    @Override
    public List<SavedGame> getGamesByUserId(int userId) {
        List<GameEntity> gameEntities = gameRepository.getGamesByUserId(userId);
        List<SavedGame> gameList = new ArrayList<>();

        for (GameEntity gameEntity : gameEntities) {
            gameList.add(modelMapper.map(gameEntity, SavedGame.class));
        }

        return gameList;
    }

    @Override
    public Game getGameById(int id) {
        GameEntity gameEntity = gameRepository.getGameById(id);

        if (Objects.isNull(gameEntity)) {
            return null;
        }

        Queue<AbstractPlayer> playerList = playerService.getAllPlayersByGameId(id);

        Game game = modelMapper.map(gameEntity, Game.class);
        game.setDifficulty(gameEntity.getDifficulty().getDifficulty());
        game.setPlayers(playerList);

        return game;
    }

    @Override
    public GameEntity saveOrUpdateGame(Game game) {
        GameEntity gameEntity = modelMapper.map(game, GameEntity.class);

        if (Objects.isNull(game.getIdGame())) {
            gameEntity.setCreatedAt(LocalDateTime.now());
        }

        DifficultyEntity difficultyEntity = gameRepository.getGameDifficultyByName(game.getDifficulty());
        gameEntity.setDifficulty(difficultyEntity);
        gameEntity.setUpdatedAt(LocalDateTime.now());

        Queue<AbstractPlayer> playerList = game.getPlayers();
        List<PlayerEntity> playerEntityList = new ArrayList<>();
        List<DeedByPlayerEntity> deedByPlayerEntities = new ArrayList<>();

        for (AbstractPlayer player : playerList) {
            PlayerEntity playerEntity = modelMapper.map(player, PlayerEntity.class);
            ColorEntity colorEntity = gameRepository.getColorByName(player.getColor());
            AbstractBox box = Dashboard.getInstance().findBoxByPlayer(player);
            BoxEntity boxEntity = modelMapper.map(box, BoxEntity.class);
            boxEntity.setBoxType(boxService.getBoxTypeByName(box.getBoxType()));

            playerEntity.setColor(colorEntity);
            playerEntity.setGame(gameEntity);
            playerEntity.setGameStyle(playerService.getGameStyleByName(player.getGameStyle()));
            playerEntity.setBox(boxEntity);

            if (player instanceof HumanPlayer) {
                UserEntity userEntity = modelMapper.map(Session.getUser(), UserEntity.class);
                playerEntity.setUser(userEntity);
            }

            for (AbstractDeed deed : player.getDeeds()) {
                DeedByPlayerEntity deedByPlayerEntity = new DeedByPlayerEntity();
                AbstractDeed deedToGetId = deedService.getDeedByName(deed.getName());

                DeedEntity deedEntity = modelMapper.map(deed, DeedEntity.class);
                deedEntity.setIdDeed(deedToGetId.getIdDeed());

                deedByPlayerEntity.setDeed(deedEntity);
                deedByPlayerEntity.setPlayer(playerEntity);
                deedByPlayerEntity.setIsPurchased(deed.getIsPurchased());
                deedByPlayerEntity.setIsMortgaged(deed.getIsMortgaged());

                if (deed instanceof Province province) {
                    deedByPlayerEntity.setFarmQuantity(province.getFarmQuantity());
                    deedByPlayerEntity.setHasRanch(province.getHasRanch());
                }

                deedByPlayerEntities.add(deedByPlayerEntity);
            }

            playerEntityList.add(playerEntity);
        }

        return gameRepository.saveOrUpdateGame(gameEntity, playerEntityList, deedByPlayerEntities);
    }

    @Override
    public void deleteGameById(int id) {
        gameRepository.deleteGameById(id);
    }
}
