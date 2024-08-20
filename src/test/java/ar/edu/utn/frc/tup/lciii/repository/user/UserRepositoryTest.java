package ar.edu.utn.frc.tup.lciii.repository.user;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.user.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRepositoryTest {

    private UserRepository userRepository;
    private Session mockSession;
    private Transaction mockTransaction;
    private Query<UserEntity> mockQuery;
    private SessionFactory originalSessionFactory;

    @BeforeEach
    void setUp() {
        userRepository = UserRepository.getInstance();
        mockSession = mock(Session.class);
        mockTransaction = mock(Transaction.class);
        mockQuery = mock(Query.class);

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
    void testFindByUsername() {
        String userName = "testUser";
        UserEntity mockUser = new UserEntity();
        mockUser.setUserName(userName);

        when(mockSession.createQuery("from UserEntity where userName = :userName", UserEntity.class)).thenReturn(mockQuery);
        when(mockQuery.setParameter("userName", userName)).thenReturn(mockQuery);
        when(mockQuery.uniqueResultOptional()).thenReturn(Optional.of(mockUser));

        Optional<UserEntity> result = userRepository.findByUsername(userName);

        assertTrue(result.isPresent());
        assertEquals(userName, result.get().getUserName());
    }

    @Test
    void testFindById() {
        int userId = 1;
        UserEntity mockUser = new UserEntity();
        mockUser.setIdUser(userId);

        when(mockSession.get(UserEntity.class, userId)).thenReturn(mockUser);

        UserEntity result = userRepository.findById(userId);

        assertNotNull(result);
        assertEquals(userId, result.getIdUser());
    }

    @Test
    void testSave() {
        UserEntity mockUser = new UserEntity();
        mockUser.setUserName("testUser");

        when(mockSession.beginTransaction()).thenReturn(mockTransaction);

        UserEntity result = userRepository.save(mockUser);

        verify(mockSession).merge(mockUser);
        verify(mockTransaction).commit();
        assertEquals(mockUser, result);
    }

    @Test
    void testSingletonInstance() {
        UserRepository instance1 = UserRepository.getInstance();
        UserRepository instance2 = UserRepository.getInstance();
        assertSame(instance1, instance2);
    }
}
