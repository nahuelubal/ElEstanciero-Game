package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxTypeEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class BoxRepositoryTest {
    private BoxRepository boxRepository;
    private Session mockSession;
    private Transaction mockTransaction;
    private Query<BoxEntity> mockQuery;
    private Query<PlayerEntity> mockQueryPlayer;
    private Query<BoxTypeEntity> mockQueryBoxType;
    private SessionFactory originalSessionFactory;

    @BeforeEach
    void setUp() {
        boxRepository = BoxRepository.getInstance();
        mockSession = mock(Session.class);
        mockTransaction = mock(Transaction.class);
        mockQuery = mock(Query.class);
        mockQueryPlayer = mock(Query.class);
        mockQueryBoxType = mock(Query.class);

        originalSessionFactory = HibernateUtil.getSessionFactory();

        SessionFactory mockSessionFactory = mock(SessionFactory.class);
        when(mockSessionFactory.openSession()).thenReturn(mockSession);

        setMockSessionFactory(mockSessionFactory);
    }

    @AfterEach
    void tearDown() {
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
    void getAllBoxes() {
        BoxEntity box = new BoxEntity();
        box.setIdBox(1);
        BoxEntity box2 = new BoxEntity();
        box.setIdBox(2);
        List<BoxEntity> mockBoxesList = new ArrayList<>();
        mockBoxesList.add(box);
        mockBoxesList.add(box2);
        when(mockSession.createQuery("FROM BoxEntity", BoxEntity.class)).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(mockBoxesList);

        List<BoxEntity> result = boxRepository.getAllBoxes();

        assertNotNull(result);
        assertEquals(mockBoxesList.size(), result.size());
        assertEquals(mockBoxesList.get(0).getIdBox(), result.get(0).getIdBox());
    }

    @Test
    void getAllPlayersByBoxId() {
        BoxEntity box = new BoxEntity();
        box.setIdBox(1);
        PlayerEntity mockPlayer = new PlayerEntity();
        mockPlayer.setIdPlayer(1);
        mockPlayer.setBox(box);
        PlayerEntity mockPlayer1 = new PlayerEntity();
        mockPlayer1.setIdPlayer(2);
        mockPlayer.setBox(box);
        List<PlayerEntity> mockPlayerList = new ArrayList<>();
        mockPlayerList.add(mockPlayer);
        mockPlayerList.add(mockPlayer1);


        when(mockSession.createQuery("FROM PlayerEntity p WHERE p.box.idBox = :boxId", PlayerEntity.class)).thenReturn(mockQueryPlayer);
        when(mockQueryPlayer.setParameter("id_box", box.getIdBox())).thenReturn(mockQueryPlayer);
        when(mockQueryPlayer.list()).thenReturn(mockPlayerList);

        List<PlayerEntity> result = boxRepository.getAllPlayersByBoxId(box.getIdBox());

        assertNotNull(result);
        assertEquals(mockPlayerList.size(), result.size());
        assertEquals(mockPlayerList.get(0).getBox(), result.get(0).getBox());
    }

    @Test
    void saveOrUpdateBox() {
        BoxEntity box = new BoxEntity();
        PlayerEntity mockPlayer = new PlayerEntity();
        List<PlayerEntity> mockPlayerList = Collections.singletonList(mockPlayer);
        when(mockSession.beginTransaction()).thenReturn(mockTransaction);

        boxRepository.saveOrUpdateBox(box, mockPlayerList);

        verify(mockSession).merge(box);
        verify(mockSession).merge(mockPlayer);
        verify(mockTransaction).commit();
    }

    @Test
    void findBoxById() {
        BoxEntity expected = new BoxEntity();
        expected.setIdBox(1);

        when(mockSession.get(BoxEntity.class, 1)).thenReturn(expected);

        BoxEntity result = boxRepository.findBoxById(1);

        assertNotNull(result);
        assertEquals(expected.getIdBox(), result.getIdBox());
    }

    @Test
    void getBoxTypeByName() {
        BoxType boxType = BoxType.START_BOX;
        BoxTypeEntity expected = new BoxTypeEntity();
        expected.setIdBoxType(1);
        expected.setBoxTypeName(boxType);


        when(mockSession.createQuery("FROM BoxTypeEntity b WHERE b.boxTypeName = :boxType", BoxTypeEntity.class)).thenReturn(mockQueryBoxType);
        when(mockQueryBoxType.setParameter("boxType", boxType)).thenReturn(mockQueryBoxType);
        when(mockQueryBoxType.uniqueResult()).thenReturn(expected);

        BoxTypeEntity result = boxRepository.getBoxTypeByName(boxType);

        assertNotNull(result);
        assertEquals(expected.getBoxTypeName(), result.getBoxTypeName());
    }
}
