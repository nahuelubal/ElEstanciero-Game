package ar.edu.utn.frc.tup.lciii.repository.game;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.config.ColorEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.DifficultyEntity;
import ar.edu.utn.frc.tup.lciii.entities.config.GameEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.DeedByPlayerEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.game.IGameRepository;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.Difficulty;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GameRepository implements IGameRepository {
	private static class Holder {
		private static final GameRepository INSTANCE = new GameRepository();
	}

	public static GameRepository getInstance() {
		return GameRepository.Holder.INSTANCE;
	}

	@Override
	public List<GameEntity> getGamesByUserId(int userId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM GameEntity g WHERE g.user.idUser =:userId", GameEntity.class)
					.setParameter("userId", userId)
					.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return List.of();
	}

	/**
	 * Aca vamos a manejar tanto el createGame() y updateGame()
	 * ya que merge permite manejar los 2 estados segun sea lo correcto en la bd
	 * Si ya existe en la BD, ya a hacer un UPDATE y si no existe un INSERT
	 */
	@Override
	public GameEntity saveOrUpdateGame(GameEntity gameEntity, List<PlayerEntity> playersList, List<DeedByPlayerEntity> deedByPlayerEntities) {
		Transaction transaction = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			// Save Game
			GameEntity gameSaved = session.merge(gameEntity);

			String hqlDelete = "DELETE FROM DeedByPlayerEntity d WHERE d.player.game.idGame = :gameId";
			session.createQuery(hqlDelete)
					.setParameter("gameId", gameSaved.getIdGame())
					.executeUpdate();

			// Save Players
			for (PlayerEntity player : playersList) {
				player.setGame(gameSaved);
				PlayerEntity playerSaved = session.merge(player);

				// Save Deeds
				for (DeedByPlayerEntity deedByPlayer : deedByPlayerEntities) {
					if (playerSaved.getColor().getColor().equals(deedByPlayer.getPlayer().getColor().getColor())) {
						deedByPlayer.setPlayer(playerSaved);
						if (deedByPlayer.getFarmQuantity() == null) {
							deedByPlayer.setFarmQuantity(0);
						}
						if (deedByPlayer.getHasRanch() == null) {
							deedByPlayer.setHasRanch(false);
						}
						session.merge(deedByPlayer);
					}
				}
			}

			transaction.commit();
			return gameSaved;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public void deleteGameById(int gameId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			GameEntity gameEntity = session.get(GameEntity.class, gameId);
			if (gameEntity != null) {
				session.remove(gameEntity);
			}
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

	@Override
	public GameEntity getGameById(int id) {
		Transaction transaction = null;
		GameEntity gameEntity = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		try {
			transaction = session.beginTransaction();
			gameEntity = session.get(GameEntity.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
				e.printStackTrace();
			}
		} finally {
			session.close();
		}
		return gameEntity;
	}

	@Override
	public ColorEntity getColorByName(Color color) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String colorName = color.name().toLowerCase();
			return session.createQuery("FROM ColorEntity c WHERE lower(c.color) = :color ", ColorEntity.class)
					.setParameter("color", colorName)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public DifficultyEntity getGameDifficultyByName(Difficulty difficulty) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			String difficultyName = difficulty.name().toLowerCase();
			return session.createQuery("FROM DifficultyEntity d WHERE lower(d.difficulty) = :difficulty ", DifficultyEntity.class)
					.setParameter("difficulty", difficultyName)
					.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
