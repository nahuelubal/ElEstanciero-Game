package ar.edu.utn.frc.tup.lciii.model.config;

import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import lombok.Data;

@Data
public class Bank {

	//Atributes
	private int idBank;
	private int moneyAvailable;
	private static Bank instance;

	//Constructor

	private Bank() {
	}

	public static Bank getInstance(){
		if (instance == null){
			instance = new Bank();
		}
		return instance;
	}

	//Methods
	public void pay(int money) {
		this.moneyAvailable -= money;
	}

	public void receive(int money) {
		this.moneyAvailable += money;
	}
}
