package ar.edu.utn.frc.tup.lciii.repository.player;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameStyleEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.player.IPlayerRepository;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.player.BotPlayerTypes;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class PlayerRepository implements IPlayerRepository {
	private static class Holder {
		private static final PlayerRepository INSTANCE = new PlayerRepository();
	}

	public static PlayerRepository getInstance() {
		return PlayerRepository.Holder.INSTANCE;
	}

	@Override
	public List<PlayerEntity> getAll() {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from PlayerEntity", PlayerEntity.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<PlayerEntity> getPlayersByGameId(int gameId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM PlayerEntity p WHERE p.game.idGame = :gameId", PlayerEntity.class)
					.setParameter("gameId", gameId)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public GameStyleEntity getGameStyleByName(GameStyle gameStyle) {
		if (gameStyle == null) {
			return null;
		}
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM GameStyleEntity g WHERE g.gameStyle = :styleGame", GameStyleEntity.class)
					.setParameter("styleGame", gameStyle.getStyleGame())
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public GameStyleEntity getGameStyleByBotType(BotPlayerTypes botPlayerType) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM GameStyleEntity g WHERE g.gameStyle = :botPlayerType", GameStyleEntity.class)
					.setParameter("botPlayerType", botPlayerType)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public BoxEntity getBoxById(int boxId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM BoxEntity box WHERE box.idBox = :boxId", BoxEntity.class)
					.setParameter("boxId", boxId)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveOrUpdatePlayer(PlayerEntity playerEntity) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			session.merge(playerEntity);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
