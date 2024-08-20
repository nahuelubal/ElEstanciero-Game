package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxEntity;
import ar.edu.utn.frc.tup.lciii.entities.box.BoxTypeEntity;
import ar.edu.utn.frc.tup.lciii.entities.player.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.interfaces.box.IBoxRepository;
import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import ar.edu.utn.frc.tup.lciii.model.box.TaxBox;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.PropertyBox;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.ProvinceProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.RailwayProperty;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.player.*;
import ar.edu.utn.frc.tup.lciii.services.BoxService;
import ar.edu.utn.frc.tup.lciii.services.DeedService;
import ar.edu.utn.frc.tup.lciii.services.TaxService;
import org.modelmapper.ModelMapper;

import java.util.*;

public class BoxServiceImpl implements BoxService {
    private final IBoxRepository boxRepository;
    private final TaxService taxService;
    private final DeedService deedService;
    private final ModelMapper modelMapper;

    public BoxServiceImpl(IBoxRepository boxRepository, TaxService taxService, DeedService deedService, ModelMapper modelMapper) {
        this.boxRepository = boxRepository;
        this.taxService = taxService;
        this.modelMapper = modelMapper;
        this.deedService = deedService;
    }

    private static class Holder {
        private static BoxServiceImpl INSTANCE;
    }

    public static BoxServiceImpl getInstance(IBoxRepository boxRepository, TaxService taxService, DeedService deedService, ModelMapper modelMapper) {
        if (BoxServiceImpl.Holder.INSTANCE == null) {
            BoxServiceImpl.Holder.INSTANCE = new BoxServiceImpl(boxRepository, taxService, deedService, modelMapper);
        }
        return BoxServiceImpl.Holder.INSTANCE;
    }

    @Override
    public List<AbstractBox> getBoxes() {
        List<AbstractBox> boxes = new ArrayList<>();
        List<BoxEntity> boxEntities = boxRepository.getAllBoxes();

        for (BoxEntity boxEntity : boxEntities) {
            AbstractBox boxToAdd = modelMapper.map(boxEntity, BoxFactoryImpl.getTypeOfBox(boxEntity.getBoxType().getBoxTypeName()));
            if (boxToAdd instanceof TaxBox) {
                TaxBox taxBox = taxService.getTaxByBoxId(boxEntity.getIdBox());
                ((TaxBox) boxToAdd).setTaxAmount(taxBox.getTaxAmount());
            }

            if (boxToAdd instanceof PropertyBox) {
                AbstractDeed deed = deedService.getDeedByBoxId(boxToAdd.getIdBox());
                switch (deed.getDeedType()) {
                    case PROVINCE:
                        ((ProvinceProperty) boxToAdd).setDeed(deed);
                        break;
                    case COMPANY:
                        ((CompanyProperty) boxToAdd).setDeed(deed);
                        break;
                    case RAILWAY:
                        ((RailwayProperty) boxToAdd).setDeed(deed);
                        break;
                }
            }

            boxes.add(boxToAdd);
        }

        boxes.sort(Comparator.comparing(AbstractBox::getIdBox));
        return boxes;
    }

    @Override
    public List<AbstractPlayer> getPlayersByBox(int boxId) {
        List<PlayerEntity> playerEntities = boxRepository.getAllPlayersByBoxId(boxId);
        List<AbstractPlayer> playerList = new ArrayList<>();

        for (PlayerEntity playerEntity : playerEntities) {
//            AbstractPlayer abstractPlayer = modelMapper.map(playerEntity, AbstractPlayer.class);
//            players.add(abstractPlayer);
            if (playerEntity.getGameStyle().getGameStyle().equals(BotPlayerTypes.CONSERVATIVE)) {
                playerList.add(modelMapper.map(playerEntity, ConservativeBotPlayer.class));
            } else if (playerEntity.getGameStyle().getGameStyle().equals(BotPlayerTypes.WELL_BALANCED)) {
                playerList.add(modelMapper.map(playerEntity, WellBalancedBotPlayer.class));
            } else if (playerEntity.getGameStyle().getGameStyle().equals(BotPlayerTypes.AGGRESSIVE)) {
                playerList.add(modelMapper.map(playerEntity, AggressiveBotPlayer.class));
            } else {
                playerList.add(modelMapper.map(playerEntity, HumanPlayer.class));
            }
        }

        return playerList;
    }

    @Override
    public void saveOrUpdateBox(AbstractBox box) {
        BoxEntity boxEntity = modelMapper.map(box, BoxEntity.class);

        List<AbstractPlayer> players = box.getPlayers();
        List<PlayerEntity> playerEntityList = new ArrayList<>();
        for (AbstractPlayer abstractPlayer : players) {
            PlayerEntity playerEntity = modelMapper.map(abstractPlayer, PlayerEntity.class);
            playerEntityList.add(playerEntity);
        }
        boxRepository.saveOrUpdateBox(boxEntity, playerEntityList);
    }

    @Override
    public AbstractBox findBoxById(int boxId) {
        BoxEntity boxEntity = boxRepository.findBoxById(boxId);

        if (Objects.isNull(boxEntity)) {
            return null;
        }

        AbstractBox abstractBox = modelMapper.map(boxEntity, AbstractBox.class);
        abstractBox.setBoxType(boxEntity.getBoxType().getBoxTypeName());
        return abstractBox;
    }

    @Override
    public BoxTypeEntity getBoxTypeByName(BoxType boxType) {
        return boxRepository.getBoxTypeByName(boxType);
    }
}
