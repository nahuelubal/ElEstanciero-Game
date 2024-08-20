package ar.edu.utn.frc.tup.lciii.interfaces.box;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxTypeEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.model.box.BoxType;

import java.util.List;

public interface IBoxRepository {
    List<BoxEntity> getAllBoxes();

    List<PlayerEntity> getAllPlayersByBoxId(int boxId);

    void saveOrUpdateBox(BoxEntity box, List<PlayerEntity> players);

    BoxEntity findBoxById(int boxId);

    BoxTypeEntity getBoxTypeByName(BoxType boxType);
}
