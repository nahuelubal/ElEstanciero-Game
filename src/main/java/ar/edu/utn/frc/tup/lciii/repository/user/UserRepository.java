package ar.edu.utn.frc.tup.lciii.repository.user;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.entities.user.UserEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.user.IUserRepository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class UserRepository implements IUserRepository {
    private static class Holder {
        private static final UserRepository INSTANCE = new UserRepository();
    }

    public static UserRepository getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<UserEntity> findByUsername(String userName) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from UserEntity where userName = :userName", UserEntity.class)
                    .setParameter("userName", userName)
                    .uniqueResultOptional();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public UserEntity findById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(UserEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public UserEntity save(UserEntity userEntity) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.merge(userEntity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return userEntity;
    }
}
