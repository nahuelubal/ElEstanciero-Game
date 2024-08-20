package ar.edu.utn.frc.tup.lciii.interfaces.user;

import ar.edu.utn.frc.tup.lciii.entities.user.UserEntity;

import java.util.Optional;

public interface IUserRepository {
    Optional<UserEntity> findByUsername(String userName);
    UserEntity save(UserEntity userEntity);
    UserEntity findById(int id);
}
