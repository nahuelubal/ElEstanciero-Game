package ar.edu.utn.frc.tup.lciii.repository;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.card.CardEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.card.ICardRepository;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CardRepository implements ICardRepository {

	private static class Holder {
		private static final ICardRepository INSTANCE = new CardRepository();
	}

	public static ICardRepository getInstance() {
		return CardRepository.Holder.INSTANCE;
	}

	@Override
	public List<CardEntity> findAll() {
		List<CardEntity> cards = new ArrayList<>();
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("from CardEntity", CardEntity.class).list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cards;
	}
}
