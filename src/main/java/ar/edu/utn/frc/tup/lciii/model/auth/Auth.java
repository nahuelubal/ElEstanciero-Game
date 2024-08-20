package ar.edu.utn.frc.tup.lciii.model.auth;

import ar.edu.utn.frc.tup.lciii.entities.user.UserEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.auth.IAuth;
import ar.edu.utn.frc.tup.lciii.model.session.Session;
import ar.edu.utn.frc.tup.lciii.model.user.User;
import ar.edu.utn.frc.tup.lciii.repository.user.UserRepository;
import ar.edu.utn.frc.tup.lciii.utils.ResponseHandler;
import org.modelmapper.ModelMapper;

import java.util.Optional;

public class Auth implements IAuth {
    UserRepository userRepository;
    ModelMapper modelMapper;

    private static class Holder {
        private static Auth INSTANCE;
    }

    public static Auth getInstance(UserRepository userRepository, ModelMapper modelMapper) {
        if (Auth.Holder.INSTANCE == null) {
            Auth.Holder.INSTANCE = new Auth(userRepository, modelMapper);
        }
        return Auth.Holder.INSTANCE;
    }

    public Auth(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseHandler login(User user) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(user.getUserName());

        if (optionalUserEntity.isPresent()) {
            UserEntity userFound = optionalUserEntity.get();
            if (user.getPassword().equals(userFound.getPassword())) {
                User userSaved = modelMapper.map(userFound, User.class);
                Session.setIsUserAuthenticated(true);
                Session.setUser(userSaved);
                return new ResponseHandler(true, "Successfully login!");
            } else {
                return new ResponseHandler(false, "Wrong password");
            }
        } else {
            return new ResponseHandler(false, "User not found");
        }
    }

    @Override
    public ResponseHandler logout() {
        if (Session.getIsUserAuthenticated()) {
            Session.setIsUserAuthenticated(false);
            Session.setUser(null);
            return new ResponseHandler(true, "Successfully logout!");
        }
        return new ResponseHandler(false, "You are not logged in");
    }

    @Override
    public ResponseHandler register(User user) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUsername(user.getUserName());

        if (optionalUserEntity.isPresent()) {
            return new ResponseHandler(false, "User already exists");
        }

        UserEntity userEntity = modelMapper.map(user, UserEntity.class);
        userRepository.save(userEntity);

        return new ResponseHandler(true, "Successfully registered user!");
    }

    @Override
    public ResponseHandler changePassword(String currentPassword, String newPassword) {
        int userId = Session.getUser().getIdUser();
        UserEntity userEntity = userRepository.findById(userId);

        if (userEntity.getPassword().equals(currentPassword)) {
            userEntity.setPassword(newPassword);
            UserEntity userEntitySaved = userRepository.save(userEntity);
            User userSaved = modelMapper.map(userEntitySaved, User.class);
            Session.setUser(userSaved);
            return new ResponseHandler(true, "Successfully password changed!");
        } else {
            return new ResponseHandler(false, "Current password does not match");
        }
    }
}
