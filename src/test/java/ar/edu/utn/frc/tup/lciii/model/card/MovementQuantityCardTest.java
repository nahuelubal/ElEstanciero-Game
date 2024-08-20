package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.JailBox;
import ar.edu.utn.frc.tup.lciii.model.box.StartBox;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.ProvinceProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.RailwayProperty;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovementQuantityCardTest {

    private MovementQuantityCard card;
    private HumanPlayer humanPlayer;
    private Dashboard dashboard;

    @BeforeEach
    public void setUp() {
        card = new MovementQuantityCard();

        List<AbstractBox> boxes = new ArrayList<>();
        dashboard =  new Dashboard();
        dashboard.setIdDashboard(1);

        humanPlayer = new HumanPlayer();
        humanPlayer.setIdPlayer(15);

        AbstractBox start = new StartBox();
        start.setIdBox(1);
        start.setBoxNumber(1);
        AbstractBox property = new ProvinceProperty();
        property.setIdBox(2);
        property.setBoxNumber(2);
        AbstractBox railway = new RailwayProperty();
        railway.setIdBox(3);
        railway.setBoxNumber(3);
        AbstractBox company = new CompanyProperty();
        company.setIdBox(4);
        company.setBoxNumber(4);
        AbstractBox jailBox = new JailBox();
        jailBox.setIdBox(5);
        jailBox.setBoxNumber(5);

        railway.addPlayerToBox(humanPlayer);
        boxes.add(start);
        boxes.add(property);
        boxes.add(railway);
        boxes.add(company);
        boxes.add(jailBox);

        dashboard.setBoxes(boxes);
    }

    @Test
    public void testActionCard() {
        try (MockedStatic<Dashboard> dashboardMockedStatic = Mockito.mockStatic(Dashboard.class)) {
            dashboardMockedStatic.when(Dashboard::getInstance).thenReturn(dashboard);
            card.setValue(1);
            card.actionCard(humanPlayer);

            assertEquals(2,dashboard.findBoxByPlayer(humanPlayer).getIdBox());
        }
    }
    @Test
    public void testActionCardV2() {
        try (MockedStatic<Dashboard> dashboardMockedStatic = Mockito.mockStatic(Dashboard.class)) {
            dashboardMockedStatic.when(Dashboard::getInstance).thenReturn(dashboard);
            card.setValue(6);
            card.actionCard(humanPlayer);

            assertEquals(2,dashboard.findBoxByPlayer(humanPlayer).getIdBox());
        }
    }

}