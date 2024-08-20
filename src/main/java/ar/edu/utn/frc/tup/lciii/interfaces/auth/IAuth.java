package ar.edu.utn.frc.tup.lciii.interfaces.auth;

import ar.edu.utn.frc.tup.lciii.model.user.User;
import ar.edu.utn.frc.tup.lciii.utils.ResponseHandler;

public interface IAuth {
    ResponseHandler login(User user);
    ResponseHandler logout();
    ResponseHandler register(User user);
    ResponseHandler changePassword(String currentPassword, String newPassword);
}
