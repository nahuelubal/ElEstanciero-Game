package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxTypeEntity;
import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;

import java.util.List;

public interface BoxService {
	List<AbstractBox> getBoxes();
	List<AbstractPlayer> getPlayersByBox(int boxId);
	void saveOrUpdateBox(AbstractBox box);
	AbstractBox findBoxById(int boxId);
	BoxTypeEntity getBoxTypeByName(BoxType boxType);
}
