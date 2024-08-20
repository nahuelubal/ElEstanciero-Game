package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Company;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.deeds.Railway;

import java.util.List;

public interface DeedService {
    List<AbstractDeed> getDeedList();

    List<AbstractDeed> getDeedListByPlayerId(int playerId);

    AbstractDeed getDeedByBoxId(int boxId);

    AbstractDeed getDeedByName(String name);

    List<Company> getCompanies();

    List<Railway> getRailways();

    List<Province> getProvincesByStyleGame(GameStyle styleGame);
}
