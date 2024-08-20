package ar.edu.utn.frc.tup.lciii.model.config;

import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.CardBox;
import ar.edu.utn.frc.tup.lciii.model.box.JailBox;
import ar.edu.utn.frc.tup.lciii.model.box.StartBox;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.ProvinceProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.RailwayProperty;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DashboardTest {
    List<AbstractBox> boxes = new ArrayList<>();
    HumanPlayer humanPlayer;
    Dashboard dashboard;

    @BeforeEach
    public void initialize(){
        dashboard = new Dashboard();
        dashboard.setIdDashboard(1);

        humanPlayer = new HumanPlayer();
        humanPlayer.setIdPlayer(15);
        humanPlayer.setColor(Color.GREEN);

        HumanPlayer humanPlayer2 = new HumanPlayer();
        humanPlayer2.setIdPlayer(15);
        humanPlayer2.setColor(Color.VIOLET);
        AbstractBox start = new StartBox();
        start.addPlayerToBox(humanPlayer);
        start.setIdBox(1);

        List<AbstractPlayer> players = new ArrayList<>();
        players.add(humanPlayer);
        players.add(humanPlayer2);
        start.setPlayers(players);

        ProvinceProperty property = new ProvinceProperty();
        property.setIdBox(2);

        AbstractBox railway = new RailwayProperty();
        railway.setIdBox(3);

        boxes.add(property);
        boxes.add(railway);

        dashboard.setBoxes(boxes);

    }

    @Test
    void paintBoxes() {
        List<AbstractBox> boxes1 = new ArrayList<>();


        HumanPlayer humanPlayer2 = new HumanPlayer();
        humanPlayer2.setIdPlayer(15);
        humanPlayer2.setColor(Color.VIOLET);
        AbstractBox start = new StartBox();
        start.addPlayerToBox(humanPlayer);
        start.setBoxNumber(1);

        List<AbstractPlayer> players = new ArrayList<>();
        players.add(humanPlayer);
        players.add(humanPlayer2);

        start.setDescription("START");
        start.setPlayers(players);
        ProvinceProperty property = new ProvinceProperty();
        property.setBoxNumber(2);
        Province province = new Province();
        province.setFarmQuantity(3);
        property.setDeed(province);
        property.setDescription("CORDOBA ZONA SUR");
        AbstractBox railway = new RailwayProperty();
        railway.setBoxNumber(3);
        railway.setDescription("GRAL. SAN MARTIN");
        AbstractBox jail = new JailBox();
        jail.setBoxNumber(4);
        jail.setDescription("CARCEL");
        AbstractBox company = new CompanyProperty();
        company.setBoxNumber(5);
        company.setDescription("INGENIO");
        AbstractBox destiny = new CardBox();
        destiny.setBoxNumber(6);
        destiny.setDescription("DESTINO");
        ProvinceProperty property1 = new ProvinceProperty();
        Province province2 = new Province();

        province2.setHasRanch(true);
        property1.setDeed(province2);
        property1.setBoxNumber(7);
        property1.setDescription("URUGUAY");

        boxes1.add(start);
        boxes1.add(property);
        boxes1.add(railway);
        boxes1.add(jail);
        boxes1.add(company);
        boxes1.add(destiny);
        boxes1.add(property1);

        dashboard.setBoxes(boxes1);
        dashboard.paintBoxes();
    }
}