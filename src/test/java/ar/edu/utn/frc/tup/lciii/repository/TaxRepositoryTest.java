package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.box.TaxEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaxRepositoryTest {

    private TaxRepository taxRepository;
    private Session mockSession;
    private Query<TaxEntity> mockTaxQuery;
    private SessionFactory originalSessionFactory;

    @BeforeEach
    void setUp() {
        taxRepository = (TaxRepository) TaxRepository.getInstance();
        mockSession = mock(Session.class);
        mockTaxQuery = mock(Query.class);

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
    void testGetTaxEntityByBoxId() {
        int boxId = 1;
        TaxEntity mockTaxEntity = new TaxEntity();

        when(mockSession.createQuery("FROM TaxEntity t WHERE t.idBox.idBox =: boxId", TaxEntity.class)).thenReturn(mockTaxQuery);
        when(mockTaxQuery.setParameter("boxId", boxId)).thenReturn(mockTaxQuery);
        when(mockTaxQuery.uniqueResult()).thenReturn(mockTaxEntity);

        TaxEntity result = taxRepository.getTaxEntityByBoxId(boxId);

        assertNotNull(result);
        assertEquals(mockTaxEntity, result);
    }
}
