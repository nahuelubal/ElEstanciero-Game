package ar.edu.utn.frc.tup.lciii.interfaces.deeds;

import ar.edu.utn.frc.tup.lciii.entities.config.StyleByProvinceEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.*;

import java.util.List;

public interface IDeedRepository {
    List<DeedEntity> getDeeds();

    List<DeedByPlayerEntity> getDeedListByPlayerId(int playerId);

    DeedEntity getDeedByBoxId(int boxId);

    DeedEntity getDeedByName(String name);

    ProvinceEntity getProvinceByDeedId(int deedId);

    CompanyEntity getCompanyByDeedId(int deedId);

    RailwayEntity getRailwayByDeedId(int deedId);

    List<RailwayEntity> getRailways();

    List<CompanyEntity> getCompanies();

    List<StyleByProvinceEntity> getProvincesByStyle(int gameStyleId);
}
