package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.dtos.game.SavedGame;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxTypeEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.ColorEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.DifficultyEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameStyleEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.DeedEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entities.user.UserEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.game.IGameRepository;
import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import ar.edu.utn.frc.tup.lciii.model.box.StartBox;
import ar.edu.utn.frc.tup.lciii.model.config.*;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.ConcreteDeedFactory;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.*;
import ar.edu.utn.frc.tup.lciii.model.user.User;
import ar.edu.utn.frc.tup.lciii.services.BoxService;
import ar.edu.utn.frc.tup.lciii.services.DeedService;
import ar.edu.utn.frc.tup.lciii.services.PlayerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class GameServiceImplTest {

	@Mock
	private IGameRepository gameRepository;
	@Mock
	private PlayerService playerService;
	@Mock
	private DeedService deedService;
	@Mock
	private BoxService boxService;
	@Mock
	private ModelMapper modelMapper;
	@InjectMocks
	private GameServiceImpl gameService;


	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testCreatePlayers_EasyDifficulty() {
		GameStyle conservativeGameStyle = new GameStyle();
		GameStyle wellBalancedGameStyle = new GameStyle();

		when(playerService.getGameStyleByBotType(BotPlayerTypes.CONSERVATIVE)).thenReturn(conservativeGameStyle);
		when(playerService.getGameStyleByBotType(BotPlayerTypes.WELL_BALANCED)).thenReturn(wellBalancedGameStyle);
		Game game = Game.getInstance();
		game.setPlayers(new LinkedList<>());
		Queue<AbstractPlayer> players = gameService.createPlayers(Difficulty.EASY);

		assertEquals(2, players.size());

		List<AbstractPlayer> playerList = new ArrayList<>(players);
        assertInstanceOf(ConservativeBotPlayer.class, playerList.get(0));
        assertInstanceOf(WellBalancedBotPlayer.class, playerList.get(1));

		assertEquals(conservativeGameStyle, playerList.get(0).getGameStyle());
		assertEquals(wellBalancedGameStyle, playerList.get(1).getGameStyle());

		verify(playerService).getGameStyleByBotType(BotPlayerTypes.CONSERVATIVE);
		verify(playerService).getGameStyleByBotType(BotPlayerTypes.WELL_BALANCED);
	}
	@Test
	public void testCreatePlayers_MediumDifficulty() {
		GameStyle conservativeGameStyle = new GameStyle();
		GameStyle wellBalancedGameStyle = new GameStyle();
		GameStyle aggressiveGameStyle = new GameStyle();

		when(playerService.getGameStyleByBotType(BotPlayerTypes.CONSERVATIVE)).thenReturn(conservativeGameStyle);
		when(playerService.getGameStyleByBotType(BotPlayerTypes.WELL_BALANCED)).thenReturn(wellBalancedGameStyle);
		when(playerService.getGameStyleByBotType(BotPlayerTypes.AGGRESSIVE)).thenReturn(aggressiveGameStyle);
		Game game = Game.getInstance();
		game.setPlayers(new LinkedList<>());
		Queue<AbstractPlayer> players = gameService.createPlayers(Difficulty.MEDIUM);

		assertEquals(3, players.size());

		List<AbstractPlayer> playerList = new ArrayList<>(players);
        assertInstanceOf(ConservativeBotPlayer.class, playerList.get(0));
        assertInstanceOf(WellBalancedBotPlayer.class, playerList.get(1));
        assertInstanceOf(AggressiveBotPlayer.class, playerList.get(2));

		assertEquals(conservativeGameStyle, playerList.get(0).getGameStyle());
		assertEquals(wellBalancedGameStyle, playerList.get(1).getGameStyle());
		assertEquals(aggressiveGameStyle, playerList.get(2).getGameStyle());

		verify(playerService).getGameStyleByBotType(BotPlayerTypes.CONSERVATIVE);
		verify(playerService).getGameStyleByBotType(BotPlayerTypes.WELL_BALANCED);
		verify(playerService).getGameStyleByBotType(BotPlayerTypes.AGGRESSIVE);
	}

	@Test
	public void testCreatePlayers_HardDifficulty() {
		GameStyle conservativeGameStyle = new GameStyle();
		GameStyle wellBalancedGameStyle = new GameStyle();
		GameStyle aggressiveGameStyle = new GameStyle();

		when(playerService.getGameStyleByBotType(BotPlayerTypes.CONSERVATIVE)).thenReturn(conservativeGameStyle);
		when(playerService.getGameStyleByBotType(BotPlayerTypes.WELL_BALANCED)).thenReturn(wellBalancedGameStyle);
		when(playerService.getGameStyleByBotType(BotPlayerTypes.AGGRESSIVE)).thenReturn(aggressiveGameStyle);
		Game game = Game.getInstance();
		game.setPlayers(new LinkedList<>());
		Queue<AbstractPlayer> players = gameService.createPlayers(Difficulty.HARD);

		assertEquals(4, players.size());

		List<AbstractPlayer> playerList = new ArrayList<>(players);
		assertTrue(playerList.get(0) instanceof ConservativeBotPlayer);
		assertTrue(playerList.get(1) instanceof WellBalancedBotPlayer);
		assertTrue(playerList.get(2) instanceof WellBalancedBotPlayer);
		assertTrue(playerList.get(3) instanceof AggressiveBotPlayer);

		assertEquals(conservativeGameStyle, playerList.get(0).getGameStyle());
		assertEquals(wellBalancedGameStyle, playerList.get(1).getGameStyle());
		assertEquals(wellBalancedGameStyle, playerList.get(2).getGameStyle());
		assertEquals(aggressiveGameStyle, playerList.get(3).getGameStyle());

		verify(playerService).getGameStyleByBotType(BotPlayerTypes.CONSERVATIVE);
		verify(playerService).getGameStyleByBotType(BotPlayerTypes.WELL_BALANCED);
		verify(playerService).getGameStyleByBotType(BotPlayerTypes.AGGRESSIVE);
	}
	@Test
	void getAllPlayersByGameId() {
		int gameId = 1;
		Queue<AbstractPlayer> players = new LinkedList<>();
		when(playerService.getAllPlayersByGameId(gameId)).thenReturn(players);

		Queue<AbstractPlayer> result =gameService.getAllPlayersByGameId(gameId);

		assertEquals(players, result);
		verify(playerService).getAllPlayersByGameId(gameId);
	}

	@Test
	void getRandomColor() {
		List<Integer> indexColors = new ArrayList<>();
		Queue<AbstractPlayer> players = new LinkedList<>();
		HumanPlayer humanPlayer = new HumanPlayer();
		humanPlayer.setColor(Color.ORANGE);
		players.add(humanPlayer);
		Game.getInstance().setPlayers(players);

		Color result = gameService.getRandomColor(indexColors);

		assertNotNull(result);
		assertTrue(indexColors.contains(result.ordinal()));
	}
	@Test
	public void testGetGamesByUserId() {
		UserEntity user = new UserEntity();
		user.setIdUser(1);

		GameEntity gameEntity = new GameEntity();
		gameEntity.setUser(user);

		List<GameEntity> gameEntities = new ArrayList<>();
		gameEntities.add(gameEntity);

		when(gameRepository.getGamesByUserId(anyInt())).thenReturn(gameEntities);
		List<SavedGame> savedGames = gameService.getGamesByUserId(user.getIdUser());

		SavedGame savedGame = modelMapper.map(gameEntity, SavedGame.class);
		savedGames.add(savedGame);
	}

	@Test
	void getGameById() {
		int gameId = 1;
		GameEntity gameEntity = new GameEntity();
		DifficultyEntity difficulty = new DifficultyEntity();
		difficulty.setDifficulty(Difficulty.EASY);
		gameEntity.setDifficulty(difficulty);
		Queue<AbstractPlayer> players = new LinkedList<>();
		when(gameRepository.getGameById(gameId)).thenReturn(gameEntity);
		when(playerService.getAllPlayersByGameId(gameId)).thenReturn(players);
		Game game = Game.getInstance();
		when(modelMapper.map(gameEntity, Game.class)).thenReturn(game);

		Game result = gameService.getGameById(gameId);

		assertNotNull(result);
		assertEquals(game, result);
		assertEquals(players, result.getPlayers());
		assertEquals(Difficulty.EASY, result.getDifficulty());
		verify(gameRepository).getGameById(gameId);
		verify(playerService).getAllPlayersByGameId(gameId);
		verify(modelMapper).map(gameEntity, Game.class);
	}

	@Test
	public void testSaveOrUpdateGame_CreateNewGame() {
		Game game = Game.getInstance();
		game.setDifficulty(Difficulty.EASY);
		game.setPlayers(new LinkedList<>());

		GameEntity gameEntity = new GameEntity();
		DifficultyEntity difficultyEntity = new DifficultyEntity();

		when(modelMapper.map(game, GameEntity.class)).thenReturn(gameEntity);
		when(gameRepository.getGameDifficultyByName(Difficulty.EASY)).thenReturn(difficultyEntity);
		when(gameRepository.saveOrUpdateGame(any(GameEntity.class), anyList(), anyList())).thenReturn(gameEntity);


		GameEntity result = gameService.saveOrUpdateGame(game);


		assertNotNull(result);
		verify(gameRepository).saveOrUpdateGame(any(GameEntity.class), anyList(), anyList());
		verify(modelMapper).map(game, GameEntity.class);
		verify(gameRepository).getGameDifficultyByName(Difficulty.EASY);
	}

	@Test
	public void testSaveOrUpdateGame_UpdateExistingGame() {
		Game game = Game.getInstance();
		game.setIdGame(1);
		game.setDifficulty(Difficulty.MEDIUM);
		game.setPlayers(new LinkedList<>());

		GameEntity gameEntity = new GameEntity();
		DifficultyEntity difficultyEntity = new DifficultyEntity();

		when(modelMapper.map(game, GameEntity.class)).thenReturn(gameEntity);
		when(gameRepository.getGameDifficultyByName(Difficulty.MEDIUM)).thenReturn(difficultyEntity);
		when(gameRepository.saveOrUpdateGame(any(GameEntity.class), anyList(), anyList())).thenReturn(gameEntity);


		GameEntity result = gameService.saveOrUpdateGame(game);

		assertNotNull(result);
		verify(gameRepository).saveOrUpdateGame(any(GameEntity.class), anyList(), anyList());
		verify(modelMapper).map(game, GameEntity.class);
		verify(gameRepository).getGameDifficultyByName(Difficulty.MEDIUM);
	}


	@Test
	void deleteGameById() {
		int gameId = 1;

		gameService.deleteGameById(gameId);

		verify(gameRepository).deleteGameById(gameId);
	}
}