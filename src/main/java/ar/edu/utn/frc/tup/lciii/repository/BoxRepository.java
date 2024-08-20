package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxTypeEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.box.IBoxRepository;
import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BoxRepository implements IBoxRepository {

	private static class Holder {
		private static final BoxRepository INSTANCE = new BoxRepository();
	}

	public static BoxRepository getInstance() {
		return BoxRepository.Holder.INSTANCE;
	}

	@Override
	public List<BoxEntity> getAllBoxes() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			List<BoxEntity> boxEntities = session.createQuery("FROM BoxEntity", BoxEntity.class).list();
			return boxEntities;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List.of();
	}

	@Override
	public List<PlayerEntity> getAllPlayersByBoxId(int boxId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM PlayerEntity p WHERE p.box.idBox = :boxId", PlayerEntity.class)
					.setParameter("id_box", boxId)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List.of();
	}

	@Override
	public void saveOrUpdateBox(BoxEntity boxEntity, List<PlayerEntity> playerEntities) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.merge(boxEntity);
			for (PlayerEntity playerEntity : playerEntities) {
				session.merge(playerEntity);
			}
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public BoxEntity findBoxById(int boxId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.get(BoxEntity.class, boxId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BoxTypeEntity getBoxTypeByName(BoxType boxType) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM BoxTypeEntity b WHERE b.boxTypeName = :boxType", BoxTypeEntity.class)
					.setParameter("boxType", boxType)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
