package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxTypeEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.ColorEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameStyleEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.*;
import ar.edu.utn.frc.tup.lciii.repository.player.PlayerRepository;
import ar.edu.utn.frc.tup.lciii.services.DeedService;
import org.h2.command.dml.MergeUsing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class PlayerServiceImplTest {

    @Mock
    private PlayerRepository playerRepository;

    @Mock
    private DeedService deedService;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PlayerServiceImpl playerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getInstanceTest() {

        PlayerServiceImpl instance1 = PlayerServiceImpl.getInstance(playerRepository, deedService, modelMapper);
        PlayerServiceImpl instance2 = PlayerServiceImpl.getInstance(playerRepository, deedService, modelMapper);

        assertNotNull(instance1);
        assertEquals(instance1, instance2);
    }

    @Test
    void getAllPlayersByGameId() {
        Game game = mock(Game.class);
        when(game.getIdGame()).thenReturn(1);
        playerService.getAllPlayersByGameId(game.getIdGame());
        verify(playerRepository, times(1)).getPlayersByGameId(1);
    }
    @Test
    public void testGetAllPlayersByGameId_withConservativeBotPlayer() {
        int gameId = 1;
        PlayerEntity playerEntity = createPlayerEntityWithBotType(BotPlayerTypes.CONSERVATIVE);
        List<PlayerEntity> playerEntities = Collections.singletonList(playerEntity);
        when(playerRepository.getPlayersByGameId(gameId)).thenReturn(playerEntities);

        ConservativeBotPlayer conservativeBotPlayer = new ConservativeBotPlayer();
        when(modelMapper.map(playerEntity, ConservativeBotPlayer.class)).thenReturn(conservativeBotPlayer);

        List<AbstractDeed> deeds = Collections.emptyList();
        when(deedService.getDeedListByPlayerId(playerEntity.getIdPlayer())).thenReturn(deeds);

        GameStyleEntity gameStyleEntity = new GameStyleEntity();
        gameStyleEntity.setGameStyle(BotPlayerTypes.CONSERVATIVE);
        GameStyle gameStyle = new GameStyle();
        when(playerRepository.getGameStyleByBotType(BotPlayerTypes.CONSERVATIVE)).thenReturn(gameStyleEntity);
        when(modelMapper.map(gameStyleEntity, GameStyle.class)).thenReturn(gameStyle);

        List<Province> provinces = Collections.emptyList();
        when(deedService.getProvincesByStyleGame(gameStyle)).thenReturn(provinces);

        Queue<AbstractPlayer> players = playerService.getAllPlayersByGameId(gameId);

        assertNotNull(players);
        assertFalse(players.isEmpty());
        assertInstanceOf(ConservativeBotPlayer.class, players.peek());
        verify(playerRepository).getPlayersByGameId(gameId);
        verify(modelMapper).map(playerEntity, ConservativeBotPlayer.class);
        verify(deedService).getDeedListByPlayerId(playerEntity.getIdPlayer());
    }
    @Test
    public void testGetAllPlayersByGameId_withAggressiveBotPlayer() {
        int gameId = 1;
        PlayerEntity playerEntity = createPlayerEntityWithBotType(BotPlayerTypes.AGGRESSIVE);
        List<PlayerEntity> playerEntities = Collections.singletonList(playerEntity);
        when(playerRepository.getPlayersByGameId(gameId)).thenReturn(playerEntities);

        AggressiveBotPlayer aggressiveBotPlayer = new AggressiveBotPlayer();
        when(modelMapper.map(playerEntity, AggressiveBotPlayer.class)).thenReturn(aggressiveBotPlayer);

        List<AbstractDeed> deeds = Collections.emptyList();
        when(deedService.getDeedListByPlayerId(playerEntity.getIdPlayer())).thenReturn(deeds);

        GameStyleEntity gameStyleEntity = new GameStyleEntity();
        gameStyleEntity.setGameStyle(BotPlayerTypes.AGGRESSIVE);
        GameStyle gameStyle = new GameStyle();
        when(playerRepository.getGameStyleByBotType(BotPlayerTypes.AGGRESSIVE)).thenReturn(gameStyleEntity);
        when(modelMapper.map(gameStyleEntity, GameStyle.class)).thenReturn(gameStyle);

        List<Province> provinces = Collections.emptyList();
        when(deedService.getProvincesByStyleGame(gameStyle)).thenReturn(provinces);

        Queue<AbstractPlayer> players = playerService.getAllPlayersByGameId(gameId);

        assertNotNull(players);
        assertFalse(players.isEmpty());
        assertInstanceOf(AggressiveBotPlayer.class, players.peek());
        verify(playerRepository).getPlayersByGameId(gameId);
        verify(modelMapper).map(playerEntity, AggressiveBotPlayer.class);
        verify(deedService).getDeedListByPlayerId(playerEntity.getIdPlayer());
    }
    @Test
    public void testGetAllPlayersByGameId_withWellBalancedBotPlayer() {
        int gameId = 1;
        PlayerEntity playerEntity = createPlayerEntityWithBotType(BotPlayerTypes.WELL_BALANCED);
        List<PlayerEntity> playerEntities = Collections.singletonList(playerEntity);
        when(playerRepository.getPlayersByGameId(gameId)).thenReturn(playerEntities);

        WellBalancedBotPlayer wellBalancedBotPlayer = new WellBalancedBotPlayer();
        when(modelMapper.map(playerEntity, WellBalancedBotPlayer.class)).thenReturn(wellBalancedBotPlayer);

        List<AbstractDeed> deeds = Collections.emptyList();
        when(deedService.getDeedListByPlayerId(playerEntity.getIdPlayer())).thenReturn(deeds);

        GameStyleEntity gameStyleEntity = new GameStyleEntity();
        gameStyleEntity.setGameStyle(BotPlayerTypes.WELL_BALANCED);
        GameStyle gameStyle = new GameStyle();
        when(playerRepository.getGameStyleByBotType(BotPlayerTypes.WELL_BALANCED)).thenReturn(gameStyleEntity);
        when(modelMapper.map(gameStyleEntity, GameStyle.class)).thenReturn(gameStyle);

        List<Province> provinces = Collections.emptyList();
        when(deedService.getProvincesByStyleGame(gameStyle)).thenReturn(provinces);

        Queue<AbstractPlayer> players = playerService.getAllPlayersByGameId(gameId);

        assertNotNull(players);
        assertFalse(players.isEmpty());
        assertInstanceOf(WellBalancedBotPlayer.class, players.peek());
        verify(playerRepository).getPlayersByGameId(gameId);
        verify(modelMapper).map(playerEntity, WellBalancedBotPlayer.class);
        verify(deedService).getDeedListByPlayerId(playerEntity.getIdPlayer());
    }
    @Test
    public void testGetAllPlayersByGameId_withHumanPlayer() {
        int gameId = 1;
        PlayerEntity playerEntity = createPlayerEntityWithBotType(null);
        List<PlayerEntity> playerEntities = Collections.singletonList(playerEntity);
        when(playerRepository.getPlayersByGameId(gameId)).thenReturn(playerEntities);

        HumanPlayer humanPlayer = new HumanPlayer();
        when(modelMapper.map(playerEntity, HumanPlayer.class)).thenReturn(humanPlayer);

        List<AbstractDeed> deeds = Collections.emptyList();
        when(deedService.getDeedListByPlayerId(playerEntity.getIdPlayer())).thenReturn(deeds);

        Queue<AbstractPlayer> players = playerService.getAllPlayersByGameId(gameId);

        assertNotNull(players);
        assertFalse(players.isEmpty());
        assertInstanceOf(HumanPlayer.class, players.peek());
        verify(playerRepository).getPlayersByGameId(gameId);
        verify(modelMapper).map(playerEntity, HumanPlayer.class);
        verify(deedService).getDeedListByPlayerId(playerEntity.getIdPlayer());
    }

    @Test
    void saveOrUpdatePlayer() {
        HumanPlayer humanPlayer = new HumanPlayer();
        humanPlayer.setColor(Color.GREEN);
        humanPlayer.setIdBox(3);

        PlayerEntity playerEntity = new PlayerEntity();
        ColorEntity colorEntity = new ColorEntity();
        colorEntity.setColor("ORANGE");
        playerEntity.setColor(colorEntity);
        BoxEntity boxEntity = mock(BoxEntity.class);
        when(boxEntity.getIdBox()).thenReturn(3);

        playerEntity.setBox(boxEntity);

        when(modelMapper.map(humanPlayer, PlayerEntity.class)).thenReturn(playerEntity);

        playerService.saveOrUpdatePlayer(humanPlayer);

        verify(playerRepository, times(1)).saveOrUpdatePlayer(playerEntity);
    }

    @Test
    void getGameStyleByNameTest() {
        GameStyle gameStyle = mock(GameStyle.class);
        when(gameStyle.getStyleGame()).thenReturn(BotPlayerTypes.AGGRESSIVE);

        GameStyleEntity gameStyleEntity = new GameStyleEntity();
        gameStyleEntity.setGameStyle(BotPlayerTypes.AGGRESSIVE);

        when(playerRepository.getGameStyleByName(gameStyle)).thenReturn(gameStyleEntity);

        GameStyleEntity result = playerService.getGameStyleByName(gameStyle);

        assertEquals(gameStyleEntity, result);
        verify(playerRepository, times(1)).getGameStyleByName(gameStyle);
    }

    @Test
    void getGameStyleByBotTypeTest() {

        GameStyleEntity gameStyleEntity = new GameStyleEntity();
        gameStyleEntity.setGameStyle(BotPlayerTypes.AGGRESSIVE);

        GameStyle gameStyle = new GameStyle();
        gameStyle.setStyleGame(BotPlayerTypes.AGGRESSIVE);

        Province province1 = new Province();
        province1.setName("Province1");

        Province province2 = new Province();
        province2.setName("Province2");

        List<Province> provinceList = Arrays.asList(province1, province2);

        when(playerRepository.getGameStyleByBotType(BotPlayerTypes.AGGRESSIVE)).thenReturn(gameStyleEntity);
        when(modelMapper.map(gameStyleEntity, GameStyle.class)).thenReturn(gameStyle);
        when(deedService.getProvincesByStyleGame(gameStyle)).thenReturn(provinceList);

        GameStyle result = playerService.getGameStyleByBotType(BotPlayerTypes.AGGRESSIVE);

        assertEquals(gameStyle, result);
        assertEquals(provinceList, result.getProvincesToPrioritize());
        verify(playerRepository, times(1)).getGameStyleByBotType(BotPlayerTypes.AGGRESSIVE);
        verify(modelMapper, times(1)).map(gameStyleEntity, GameStyle.class);
        verify(deedService, times(1)).getProvincesByStyleGame(gameStyle);
    }

    @Test
    void getBoxByIdTest() {
        BoxTypeEntity boxTypeEntity = mock(BoxTypeEntity.class);
        when(boxTypeEntity.getBoxTypeName()).thenReturn(BoxType.TAX_BOX);

        BoxEntity boxEntity = mock(BoxEntity.class);
        when(boxEntity.getBoxType()).thenReturn(boxTypeEntity);
        when(boxEntity.getIdBox()).thenReturn(7);

        when(playerRepository.getBoxById(7)).thenReturn(boxEntity);

        BoxEntity resultado = playerService.getBoxById(boxEntity.getIdBox());

        assertEquals(resultado, boxEntity);
    }
    private PlayerEntity createPlayerEntityWithBotType(BotPlayerTypes botType) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setIdPlayer(1);
        playerEntity.setColor(new ColorEntity());
        playerEntity.getColor().setColor("RED");
        playerEntity.setBox(new BoxEntity());
        playerEntity.getBox().setIdBox(1);

        if (botType != null) {
            GameStyleEntity gameStyleEntity = new GameStyleEntity();
            gameStyleEntity.setIdGameStyle(1);
            playerEntity.setGameStyle(gameStyleEntity);
            playerEntity.getGameStyle().setGameStyle(botType);
        }
        return playerEntity;
    }
}
