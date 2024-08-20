package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.config.StyleByProvinceEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.*;
import ar.edu.utn.frc.tup.lciii.interfaces.deeds.IDeedRepository;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class DeedRepository implements IDeedRepository {

	private static class Holder {
		private static final IDeedRepository INSTANCE = new DeedRepository();
	}

	public static IDeedRepository getInstance() {
		return Holder.INSTANCE;
	}

	@Override
	public List<DeedEntity> getDeeds() {
		List<DeedEntity> deedEntities = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from DeedEntity ", DeedEntity.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deedEntities;
	}

	@Override
	public List<DeedByPlayerEntity> getDeedListByPlayerId(int playerId) {
		List<DeedByPlayerEntity> deedEntities = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from DeedByPlayerEntity d WHERE d.player.idPlayer =: playerId", DeedByPlayerEntity.class)
					.setParameter("playerId", playerId)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deedEntities;
	}

	@Override
	public DeedEntity getDeedByBoxId(int boxId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM DeedEntity d WHERE d.box.idBox =: boxId", DeedEntity.class)
					.setParameter("boxId", boxId)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DeedEntity getDeedByName(String name) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM DeedEntity d WHERE d.deedName =: name", DeedEntity.class)
					.setParameter("name", name)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ProvinceEntity getProvinceByDeedId(int deedId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM ProvinceEntity p WHERE p.deed.id =: deedId", ProvinceEntity.class)
					.setParameter("deedId", deedId)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CompanyEntity getCompanyByDeedId(int deedId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM CompanyEntity c WHERE c.deed.id =: deedId", CompanyEntity.class)
					.setParameter("deedId", deedId)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RailwayEntity getRailwayByDeedId(int deedId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM RailwayEntity r WHERE r.deed.id =: deedId", RailwayEntity.class)
					.setParameter("deedId", deedId)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RailwayEntity> getRailways() {
		List<RailwayEntity> railwayEntities = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from RailwayEntity ", RailwayEntity.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return railwayEntities;
	}

	@Override
	public List<CompanyEntity> getCompanies() {
		List<CompanyEntity> companyEntities = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from CompanyEntity ", CompanyEntity.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return companyEntities;
	}

	@Override
	public List<StyleByProvinceEntity> getProvincesByStyle(int gameStyleId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from StyleByProvinceEntity s where s.idGameStyle.idGameStyle = :idGameStyle", StyleByProvinceEntity.class)
					.setParameter("idGameStyle", gameStyleId)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
