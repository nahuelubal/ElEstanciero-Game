package ar.edu.utn.frc.tup.lciii;

import ar.edu.utn.frc.tup.lciii.others.HibernateUtil;
import ar.edu.utn.frc.tup.lciii.views.WelcomeView;

/**
 * Hello to TPI Estanciero
 */
public class
App {

	/**
	 * This is the main program
	 */
	public static void main(String[] args) {
		HibernateUtil.getSessionFactory().openSession();
		new WelcomeView().render();
	}
}
