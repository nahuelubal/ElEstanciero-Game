package ar.edu.utn.frc.tup.lciii.model.session;

import ar.edu.utn.frc.tup.lciii.model.user.User;

public class Session {
	private static boolean isUserAuthenticated;
	private static User user;

	public Session() {
		isUserAuthenticated = false;
	}

	public static boolean getIsUserAuthenticated() {
		return isUserAuthenticated;
	}

	public static void setIsUserAuthenticated(boolean isUserAuthenticated) {
		Session.isUserAuthenticated = isUserAuthenticated;
	}

	public static User getUser() {
		if (isUserAuthenticated && user != null) {
			return user;
		}

		return null;
	}

	public static void setUser(User user) {
		Session.user = user;
	}
}
