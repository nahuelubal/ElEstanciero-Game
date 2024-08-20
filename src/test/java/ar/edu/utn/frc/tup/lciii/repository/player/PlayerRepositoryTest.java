package ar.edu.utn.frc.tup.lciii.repository.player;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameStyleEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.player.BotPlayerTypes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerRepositoryTest {

    private PlayerRepository playerRepository;
    private Session mockSession;
    private Transaction mockTransaction;
    private Query<PlayerEntity> mockPlayerQuery;
    private Query<GameStyleEntity> mockGameStyleQuery;
    private Query<BoxEntity> mockBoxQuery;
    private SessionFactory originalSessionFactory;

    @BeforeEach
    void setUp() {
        playerRepository = PlayerRepository.getInstance();
        mockSession = mock(Session.class);
        mockTransaction = mock(Transaction.class);
        mockPlayerQuery = mock(Query.class);
        mockGameStyleQuery = mock(Query.class);
        mockBoxQuery = mock(Query.class);

        // Guardo original
        originalSessionFactory = HibernateUtil.getSessionFactory();

        // Creo una SessionFactory mock que devuelve una Session mock
        SessionFactory mockSessionFactory = mock(SessionFactory.class);
        when(mockSessionFactory.openSession()).thenReturn(mockSession);

        // Reemplazo la session en HibernateUtil con la mock
        setMockSessionFactory(mockSessionFactory);
    }

    @AfterEach
    void tearDown() {
        // Restauro la original
        setMockSessionFactory(originalSessionFactory);
    }

    private void setMockSessionFactory(SessionFactory sessionFactory) {
        try {
            java.lang.reflect.Field field = HibernateUtil.class.getDeclaredField("sessionFactory");
            field.setAccessible(true);
            field.set(null, sessionFactory);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testGetAll() {
        PlayerEntity mockPlayer = new PlayerEntity();
        List<PlayerEntity> mockPlayerList = Collections.singletonList(mockPlayer);

        when(mockSession.createQuery("from PlayerEntity", PlayerEntity.class)).thenReturn(mockPlayerQuery);
        when(mockPlayerQuery.list()).thenReturn(mockPlayerList);

        List<PlayerEntity> result = playerRepository.getAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockPlayer, result.get(0));
    }

    @Test
    void testGetPlayersByGameId() {
        int gameId = 1;
        PlayerEntity mockPlayer = new PlayerEntity();
        List<PlayerEntity> mockPlayerList = Collections.singletonList(mockPlayer);

        when(mockSession.createQuery("FROM PlayerEntity p WHERE p.game.idGame = :gameId", PlayerEntity.class)).thenReturn(mockPlayerQuery);
        when(mockPlayerQuery.setParameter("gameId", gameId)).thenReturn(mockPlayerQuery);
        when(mockPlayerQuery.list()).thenReturn(mockPlayerList);

        List<PlayerEntity> result = playerRepository.getPlayersByGameId(gameId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(mockPlayer, result.get(0));
    }

    @Test
    void testGetGameStyleByName() {
        GameStyle gameStyle = new GameStyle();
        gameStyle.setStyleGame(BotPlayerTypes.WELL_BALANCED);
        GameStyleEntity mockGameStyleEntity = new GameStyleEntity();

        when(mockSession.createQuery("FROM GameStyleEntity g WHERE g.gameStyle = :styleGame", GameStyleEntity.class)).thenReturn(mockGameStyleQuery);
        when(mockGameStyleQuery.setParameter("styleGame", BotPlayerTypes.WELL_BALANCED)).thenReturn(mockGameStyleQuery);
        when(mockGameStyleQuery.uniqueResult()).thenReturn(mockGameStyleEntity);

        GameStyleEntity result = playerRepository.getGameStyleByName(gameStyle);

        assertNotNull(result);
        assertEquals(mockGameStyleEntity, result);
    }

    @Test
    void testGetBoxById() {
        int boxId = 1;
        BoxEntity mockBoxEntity = new BoxEntity();

        when(mockSession.createQuery("FROM BoxEntity box WHERE box.idBox = :boxId", BoxEntity.class)).thenReturn(mockBoxQuery);
        when(mockBoxQuery.setParameter("boxId", boxId)).thenReturn(mockBoxQuery);
        when(mockBoxQuery.uniqueResult()).thenReturn(mockBoxEntity);

        BoxEntity result = playerRepository.getBoxById(boxId);

        assertNotNull(result);
        assertEquals(mockBoxEntity, result);
    }

    @Test
    void testSaveOrUpdatePlayer() {
        PlayerEntity mockPlayer = new PlayerEntity();

        when(mockSession.beginTransaction()).thenReturn(mockTransaction);

        playerRepository.saveOrUpdatePlayer(mockPlayer);

        verify(mockSession).merge(mockPlayer);
        verify(mockTransaction).commit();
    }
}
