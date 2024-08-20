package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.config.StyleByProvinceEntity;
import ar.edu.utn.frc.tup.lciii.entities.deeds.*;
import ar.edu.utn.frc.tup.lciii.interfaces.deeds.IDeedRepository;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.*;
import ar.edu.utn.frc.tup.lciii.services.DeedService;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DeedServiceImpl implements DeedService {
    private final IDeedRepository deedRepository;
    private final ModelMapper modelMapper;

    public DeedServiceImpl(IDeedRepository deedRepository, ModelMapper modelMapper) {
        this.deedRepository = deedRepository;
        this.modelMapper = modelMapper;
    }

    private static class Holder {
        private static DeedServiceImpl INSTANCE;
    }

    public static DeedServiceImpl getInstance(IDeedRepository deedRepository, ModelMapper modelMapper) {
        if (Holder.INSTANCE == null) {
            Holder.INSTANCE = new DeedServiceImpl(deedRepository, modelMapper);
        }
        return Holder.INSTANCE;
    }

    @Override
    public List<AbstractDeed> getDeedList() {
        List<AbstractDeed> deeds = new ArrayList<>();
        List<DeedEntity> deedEntities = deedRepository.getDeeds();

        for (DeedEntity deedEntity : deedEntities) {
//            AbstractDeed deedToAdd = modelMapper.map(deedEntity, AbstractDeed.class);
//
//            deeds.add(deedToAdd);
            deeds.add(getDeedByType(deedEntity));
        }

        return deeds;
    }

    @Override
    public List<AbstractDeed> getDeedListByPlayerId(int playerId) {
        List<AbstractDeed> deeds = new ArrayList<>();
        List<DeedByPlayerEntity> deedEntities = deedRepository.getDeedListByPlayerId(playerId);

        for (DeedByPlayerEntity deedByPlayerEntity : deedEntities) {
            DeedEntity deedEntity = deedByPlayerEntity.getDeed();
            deeds.add(getDeedByType(deedEntity));
        }

        return deeds;
    }

    @Override
    public AbstractDeed getDeedByBoxId(int boxId) {
        DeedEntity deedEntity = deedRepository.getDeedByBoxId(boxId);

        return getDeedByType(deedEntity);
    }

    private AbstractDeed getDeedByType(DeedEntity deedEntity) {
        switch (deedEntity.getDeedType()) {
            case PROVINCE:
                ProvinceEntity provinceEntity = deedRepository.getProvinceByDeedId(deedEntity.getIdDeed());

                Province province = modelMapper.map(provinceEntity, Province.class);
                province.setIdDeed(deedEntity.getIdDeed());
                province.setPurchaseValue(deedEntity.getPurchaseValue());
                province.setMortgageValue(deedEntity.getMortgageValue());
                province.setDeedType(DeedType.PROVINCE);
                province.setName(deedEntity.getDeedName());
                province.setRentValue(provinceEntity.getRent());
                province.setRent_ranch(provinceEntity.getRentRanch());
                province.setRent_1_farm(provinceEntity.getRent1Farm());
                province.setRent_2_farm(provinceEntity.getRent2Farms());
                province.setRent_3_farm(provinceEntity.getRent3Farms());
                province.setRent_4_farm(provinceEntity.getRent4Farms());

                return province;
            case COMPANY:
                CompanyEntity companyEntity = deedRepository.getCompanyByDeedId(deedEntity.getIdDeed());

                Company company = modelMapper.map(companyEntity, Company.class);
                company.setIdDeed(deedEntity.getIdDeed());
                company.setPurchaseValue(deedEntity.getPurchaseValue());
                company.setMortgageValue(deedEntity.getMortgageValue());
                company.setDeedType(DeedType.COMPANY);
                company.setName(deedEntity.getDeedName());
                company.setRent_1_company(companyEntity.getRent1Company());
                company.setRent_2_company(companyEntity.getRent2Companies());
                company.setRent_3_company(companyEntity.getRent3Companies());

                return company;
            case RAILWAY:
                RailwayEntity railwayEntity = deedRepository.getRailwayByDeedId(deedEntity.getIdDeed());

                Railway railway = modelMapper.map(railwayEntity, Railway.class);
                railway.setIdDeed(deedEntity.getIdDeed());
                railway.setPurchaseValue(deedEntity.getPurchaseValue());
                railway.setMortgageValue(deedEntity.getMortgageValue());
                railway.setDeedType(DeedType.RAILWAY);
                railway.setName(deedEntity.getDeedName());
                railway.setRent_1_railway(railwayEntity.getRent1Railway());
                railway.setRent_2_railway(railwayEntity.getRent2Railways());
                railway.setRent_3_railway(railwayEntity.getRent3Railways());
                railway.setRent_4_railway(railwayEntity.getRent4Railways());

                return railway;
            default:
                return null;
        }
    }


    @Override
    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        List<CompanyEntity> companyEntities = deedRepository.getCompanies();

        for (CompanyEntity companyEntity : companyEntities) {
            Company companyToAdd = modelMapper.map(companyEntity, Company.class);

            companies.add(companyToAdd);
        }
        return companies;
    }

    @Override
    public List<Railway> getRailways() {
        List<Railway> railways = new ArrayList<>();
        List<RailwayEntity> railwayEntities = deedRepository.getRailways();

        for (RailwayEntity railwayEntity : railwayEntities) {
            Railway railwayToAdd = modelMapper.map(railwayEntity, Railway.class);

            railways.add(railwayToAdd);
        }
        return railways;
    }

    @Override
    public List<Province> getProvincesByStyleGame(GameStyle styleGame) {
        List<Province> provinces = new ArrayList<>();
        List<StyleByProvinceEntity> provinceEntities = deedRepository.getProvincesByStyle(styleGame.getIdStyleGame());

        for (StyleByProvinceEntity provinceEntity : provinceEntities) {
            Province provinceToAdd = modelMapper.map(provinceEntity.getIdProvince(), Province.class);

            provinces.add(provinceToAdd);
        }

        return provinces;
    }

    @Override
    public AbstractDeed getDeedByName(String name) {
        DeedEntity deedEntity = deedRepository.getDeedByName(name);

        return getDeedByType(deedEntity);
    }
}
