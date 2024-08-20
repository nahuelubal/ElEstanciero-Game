package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.entities.config.StyleByProvinceEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.*;
import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeedRepositoryTest {

    private DeedRepository deedRepository;
    private Session mockSession;
    private Query<DeedEntity> mockDeedQuery;
    private Query<DeedByPlayerEntity> mockDeedByPlayerQuery;
    private Query<ProvinceEntity> mockProvinceQuery;
    private Query<CompanyEntity> mockCompanyQuery;
    private Query<RailwayEntity> mockRailwayQuery;
    private Query<StyleByProvinceEntity> mockStyleByProvinceQuery;
    private SessionFactory originalSessionFactory;

    @BeforeEach
    void setUp() {
        deedRepository = (DeedRepository) DeedRepository.getInstance();
        mockSession = mock(Session.class);
        mockDeedQuery = mock(Query.class);
        mockDeedByPlayerQuery = mock(Query.class);
        mockProvinceQuery = mock(Query.class);
        mockCompanyQuery = mock(Query.class);
        mockRailwayQuery = mock(Query.class);
        mockStyleByProvinceQuery = mock(Query.class);

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
    void testGetDeeds() {
        DeedEntity mockDeed = new DeedEntity();
        List<DeedEntity> mockDeedList = Collections.singletonList(mockDeed);

        when(mockSession.createQuery("from DeedEntity ", DeedEntity.class)).thenReturn(mockDeedQuery);
        when(mockDeedQuery.list()).thenReturn(mockDeedList);

        List<DeedEntity> result = deedRepository.getDeeds();

        assertNotNull(result);
        assertEquals(mockDeed, result.get(0));
    }

    @Test
    void testGetDeedByBoxId() {
        int boxId = 1;
        DeedEntity mockDeed = new DeedEntity();

        when(mockSession.createQuery("FROM DeedEntity d WHERE d.box.idBox =: boxId", DeedEntity.class)).thenReturn(mockDeedQuery);
        when(mockDeedQuery.setParameter("boxId", boxId)).thenReturn(mockDeedQuery);
        when(mockDeedQuery.uniqueResult()).thenReturn(mockDeed);

        DeedEntity result = deedRepository.getDeedByBoxId(boxId);

        assertNotNull(result);
        assertEquals(mockDeed, result);
    }

    @Test
    void testGetProvinceByDeedId() {
        int deedId = 1;
        ProvinceEntity mockProvince = new ProvinceEntity();

        when(mockSession.createQuery("FROM ProvinceEntity p WHERE p.deed.id =: deedId", ProvinceEntity.class)).thenReturn(mockProvinceQuery);
        when(mockProvinceQuery.setParameter("deedId", deedId)).thenReturn(mockProvinceQuery);
        when(mockProvinceQuery.uniqueResult()).thenReturn(mockProvince);

        ProvinceEntity result = deedRepository.getProvinceByDeedId(deedId);

        assertNotNull(result);
        assertEquals(mockProvince, result);
    }

    @Test
    void testGetCompanyByDeedId() {
        int deedId = 1;
        CompanyEntity mockCompany = new CompanyEntity();

        when(mockSession.createQuery("FROM CompanyEntity c WHERE c.deed.id =: deedId", CompanyEntity.class)).thenReturn(mockCompanyQuery);
        when(mockCompanyQuery.setParameter("deedId", deedId)).thenReturn(mockCompanyQuery);
        when(mockCompanyQuery.uniqueResult()).thenReturn(mockCompany);

        CompanyEntity result = deedRepository.getCompanyByDeedId(deedId);

        assertNotNull(result);
        assertEquals(mockCompany, result);
    }

    @Test
    void testGetRailwayByDeedId() {
        int deedId = 1;
        RailwayEntity mockRailway = new RailwayEntity();

        when(mockSession.createQuery("FROM RailwayEntity r WHERE r.deed.id =: deedId", RailwayEntity.class)).thenReturn(mockRailwayQuery);
        when(mockRailwayQuery.setParameter("deedId", deedId)).thenReturn(mockRailwayQuery);
        when(mockRailwayQuery.uniqueResult()).thenReturn(mockRailway);

        RailwayEntity result = deedRepository.getRailwayByDeedId(deedId);

        assertNotNull(result);
        assertEquals(mockRailway, result);
    }

    @Test
    void testGetRailways() {
        RailwayEntity mockRailway = new RailwayEntity();
        List<RailwayEntity> mockRailwayList = Collections.singletonList(mockRailway);

        when(mockSession.createQuery("from RailwayEntity ", RailwayEntity.class)).thenReturn(mockRailwayQuery);
        when(mockRailwayQuery.list()).thenReturn(mockRailwayList);

        List<RailwayEntity> result = deedRepository.getRailways();

        assertNotNull(result);
        assertEquals(mockRailway, result.get(0));
    }

    @Test
    void testGetCompanies() {
        CompanyEntity mockCompany = new CompanyEntity();
        List<CompanyEntity> mockCompanyList = Collections.singletonList(mockCompany);

        when(mockSession.createQuery("from CompanyEntity ", CompanyEntity.class)).thenReturn(mockCompanyQuery);
        when(mockCompanyQuery.list()).thenReturn(mockCompanyList);

        List<CompanyEntity> result = deedRepository.getCompanies();

        assertNotNull(result);
        assertEquals(mockCompany, result.get(0));
    }

    @Test
    void testGetProvincesByStyle() {
        int gameStyleId = 1;
        StyleByProvinceEntity mockStyle = new StyleByProvinceEntity();
        List<StyleByProvinceEntity> mockStyleList = Collections.singletonList(mockStyle);

        when(mockSession.createQuery("from StyleByProvinceEntity s where s.idGameStyle.idGameStyle = :idGameStyle", StyleByProvinceEntity.class)).thenReturn(mockStyleByProvinceQuery);
        when(mockStyleByProvinceQuery.setParameter("idGameStyle", gameStyleId)).thenReturn(mockStyleByProvinceQuery);
        when(mockStyleByProvinceQuery.list()).thenReturn(mockStyleList);

        List<StyleByProvinceEntity> result = deedRepository.getProvincesByStyle(gameStyleId);

        assertNotNull(result);
        assertEquals(mockStyle, result.get(0));
    }
}
