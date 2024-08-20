package ar.edu.utn.frc.tup.lciii.model.user;

import ar.edu.utn.frc.tup.lciii.interfaces.user.IUserRepository;
import ar.edu.utn.frc.tup.lciii.interfaces.auth.IAuth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	//Attributes
	private int idUser;
	private String userName;
	private String password;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
}
