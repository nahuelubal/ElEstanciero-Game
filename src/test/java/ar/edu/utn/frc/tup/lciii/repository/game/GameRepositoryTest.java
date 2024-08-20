package ar.edu.utn.frc.tup.lciii.repository.game;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.config.ColorEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.DifficultyEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.Difficulty;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameRepositoryTest {

    private GameRepository gameRepository;
    private Session mockSession;
    private Transaction mockTransaction;
    private Query<GameEntity> mockQuery;
    private Query<ColorEntity> mockColorQuery;
    private Query<DifficultyEntity> mockDifficultyQuery;
    private SessionFactory originalSessionFactory;

    @BeforeEach
    public void setUp() {
        gameRepository = GameRepository.getInstance();
        mockSession = mock(Session.class);
        mockTransaction = mock(Transaction.class);
        mockQuery = mock(Query.class);
        mockColorQuery = mock(Query.class);
        mockDifficultyQuery = mock(Query.class);
        originalSessionFactory = HibernateUtil.getSessionFactory();

        SessionFactory mockSessionFactory = mock(SessionFactory.class);
        when(mockSessionFactory.openSession()).thenReturn(mockSession);

        setMockSessionFactory(mockSessionFactory);
    }

    @AfterEach
    void tearDown() {
        // Restauro la SessionFactory original
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
    void testGetGamesByUserId() {
        int userId = 1;
        List<GameEntity> expectedGames = new ArrayList<>();
        expectedGames.add(new GameEntity());

        when(mockSession.createQuery("FROM GameEntity g WHERE g.user.idUser =:userId", GameEntity.class)).thenReturn(mockQuery);
        when(mockQuery.setParameter("userId", userId)).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(expectedGames);

        List<GameEntity> actualGames = gameRepository.getGamesByUserId(userId);

        assertEquals(expectedGames, actualGames);

        verify(mockSession).createQuery("FROM GameEntity g WHERE g.user.idUser =:userId", GameEntity.class);
        verify(mockQuery).setParameter("userId", userId);
        verify(mockQuery).list();

    }

    @Test
    void deleteGameById() {
        int gameId = 1;
        GameEntity mockGameEntity = new GameEntity();

        when(mockSession.beginTransaction()).thenReturn(mockTransaction);
        when(mockSession.get(GameEntity.class, gameId)).thenReturn(mockGameEntity);

        gameRepository.deleteGameById(gameId);

        verify(mockSession).beginTransaction();
        verify(mockSession).get(GameEntity.class, gameId);
        verify(mockSession).remove(mockGameEntity);
        verify(mockTransaction).commit();
        verify(mockSession).close();
    }

    @Test
    void getGameById() {
        int gameId = 1;
        GameEntity mockGameEntity = new GameEntity();

        when(mockSession.beginTransaction()).thenReturn(mockTransaction);
        when(mockSession.get(GameEntity.class, gameId)).thenReturn(mockGameEntity);

        GameEntity result = gameRepository.getGameById(gameId);

        assertNotNull(result);
        assertEquals(mockGameEntity, result);

        verify(mockSession).beginTransaction();
        verify(mockSession).get(GameEntity.class, gameId);
        verify(mockTransaction).commit();
        verify(mockSession).close();
    }

    @Test
    void getColorByName() {
        Color color = Color.RED;
        ColorEntity mockColorEntity = new ColorEntity();
        mockColorEntity.setColor("red");

        when(mockSession.createQuery("FROM ColorEntity c WHERE lower(c.color) = :color ", ColorEntity.class)).thenReturn(mockColorQuery);
        when(mockColorQuery.setParameter("color", color.name().toLowerCase())).thenReturn(mockColorQuery);
        when(mockColorQuery.uniqueResult()).thenReturn(mockColorEntity);

        ColorEntity result = gameRepository.getColorByName(color);

        assertNotNull(result);
        assertEquals(mockColorEntity, result);
    }

    @Test
    void getGameDifficultyByName() {
        Difficulty difficulty = Difficulty.EASY;
        DifficultyEntity mockDifficultyEntity = new DifficultyEntity();
        mockDifficultyEntity.setDifficulty(difficulty);

        when(mockSession.createQuery("FROM DifficultyEntity d WHERE lower(d.difficulty) = :difficulty ", DifficultyEntity.class)).thenReturn(mockDifficultyQuery);
        when(mockDifficultyQuery.setParameter("difficulty", difficulty.name().toLowerCase())).thenReturn(mockDifficultyQuery);
        when(mockDifficultyQuery.uniqueResult()).thenReturn(mockDifficultyEntity);

        DifficultyEntity result = gameRepository.getGameDifficultyByName(difficulty);

        assertNotNull(result);
        assertEquals(mockDifficultyEntity, result);
    }
}
