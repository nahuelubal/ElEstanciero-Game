package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.StartBox;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.ProvinceProperty;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.RailwayProperty;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MovementBoxCardTest {

    private MovementBoxCard card;

    private HumanPlayer humanPlayer;
    private Dashboard dashboard;
    private List<AbstractBox> boxes;
    @BeforeEach
    public void setUp() {
        card = new MovementBoxCard();
        card.setBoxId(2);
        boxes = new ArrayList<>();
        dashboard =  new Dashboard();
        dashboard.setIdDashboard(1);

        humanPlayer = new HumanPlayer();
        humanPlayer.setIdPlayer(15);

        AbstractBox start = new StartBox();
        start.addPlayerToBox(humanPlayer);
        start.setIdBox(1);
        start.setBoxNumber(1);
        AbstractBox property = new ProvinceProperty();
        property.setIdBox(2);
        property.setBoxNumber(2);
        AbstractBox railway = new RailwayProperty();
        railway.setIdBox(3);
        railway.setBoxNumber(3);


        boxes.add(start);
        boxes.add(property);
        boxes.add(railway);

        dashboard.setBoxes(boxes);
    }

    @Test
    public void testActionCard() {
        try (MockedStatic<Dashboard> dashboardMockedStatic = Mockito.mockStatic(Dashboard.class)) {
            dashboardMockedStatic.when(Dashboard::getInstance).thenReturn(dashboard);

            card.actionCard(humanPlayer);

            assertEquals(dashboard.findBoxByPlayer(humanPlayer).getBoxNumber(),card.getBoxId());
        }
    }
}