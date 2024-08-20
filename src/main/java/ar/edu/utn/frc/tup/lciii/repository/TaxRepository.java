package ar.edu.utn.frc.tup.lciii.repository;


import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.box.TaxEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.box.ITaxRepository;
import org.hibernate.Session;

public class TaxRepository implements ITaxRepository {

	private static class Holder {
		private static final ITaxRepository INSTANCE = new TaxRepository();
	}

	public static ITaxRepository getInstance() {
		return Holder.INSTANCE;
	}

	@Override
	public TaxEntity getTaxEntityByBoxId(int boxId) {
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			return session.createQuery("FROM TaxEntity t WHERE t.idBox.idBox =: boxId", TaxEntity.class)
					.setParameter("boxId", boxId).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
