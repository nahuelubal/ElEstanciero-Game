package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.card.CardEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CardRepositoryTest {

    private CardRepository cardRepository;
    private Session mockSession;
    private Query<CardEntity> mockQuery;
    private SessionFactory originalSessionFactory;

    @BeforeEach
    void setUp() {
        cardRepository = (CardRepository) CardRepository.getInstance();
        mockSession = mock(Session.class);
        mockQuery = mock(Query.class);

        originalSessionFactory = HibernateUtil.getSessionFactory();

        SessionFactory mockSessionFactory = mock(SessionFactory.class);
        when(mockSessionFactory.openSession()).thenReturn(mockSession);

        setMockSessionFactory(mockSessionFactory);
    }

    @Test
    void testFindAll() {
        List<CardEntity> mockCards = new ArrayList<>();
        CardEntity mockCard = new CardEntity();
        mockCards.add(mockCard);

        //Configuramos el mockSession para que devuelva el mockQuery cuando se llame a createQuery.
        // Configuramos el mockQuery para que devuelva la lista de entidades mock cuando se llame a list.
        when(mockSession.createQuery("from CardEntity", CardEntity.class)).thenReturn(mockQuery);
        when(mockQuery.list()).thenReturn(mockCards);

        List<CardEntity> result = cardRepository.findAll();

        assertNotNull(result);
        assertEquals(mockCard, result.get(0));
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
}
