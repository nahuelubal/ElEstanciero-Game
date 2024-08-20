package ar.edu.utn.frc.tup.lciii.services.impl;

import ar.edu.utn.frc.tup.lciii.entities.box.BoxTypeEntity;
import ar.edu.utn.frc.tup.lciii.model.box.AbstractBox;
import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import ar.edu.utn.frc.tup.lciii.model.box.CardBox;
import ar.edu.utn.frc.tup.lciii.model.box.StartBox;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.PropertyBox;
import ar.edu.utn.frc.tup.lciii.model.box.property_box.ProvinceProperty;
import ar.edu.utn.frc.tup.lciii.model.card.*;
import ar.edu.utn.frc.tup.lciii.model.config.Dashboard;
import ar.edu.utn.frc.tup.lciii.model.player.AbstractPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.ConservativeBotPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.HumanPlayer;
import ar.edu.utn.frc.tup.lciii.model.player.WellBalancedBotPlayer;
import ar.edu.utn.frc.tup.lciii.services.BoxService;
import ar.edu.utn.frc.tup.lciii.services.CardService;
import org.hibernate.sql.ast.tree.expression.Star;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DashboardServiceImplTest {
    @Mock
    private BoxService boxService;
    @Mock
    private CardService cardService;
    @InjectMocks
    private DashboardServiceImpl dashboardService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void setPlayersIntoStartBox() {
        Queue<AbstractPlayer> players = new LinkedList<>();
        ConservativeBotPlayer player1 = new ConservativeBotPlayer();
        WellBalancedBotPlayer player2 = new WellBalancedBotPlayer();
        players.add(player1);
        players.add(player2);
        Dashboard dashboard = Dashboard.getInstance();
        StartBox startBox = new StartBox();
        dashboard.setBoxes(List.of(startBox));

        dashboardService.setPlayersIntoStartBox(players);

        assertEquals(2, startBox.getPlayers().size());
    }

    @Test
    public void setPlayersIntoTheirBoxes() {
        Queue<AbstractPlayer> players = new LinkedList<>();
        ConservativeBotPlayer player1 = new ConservativeBotPlayer();
        player1.setIdBox(1);
        WellBalancedBotPlayer player2 = new WellBalancedBotPlayer();
        player2.setIdBox(2);
        players.add(player1);
        players.add(player2);

        List<AbstractBox> boxes = new ArrayList<>();
        StartBox box1 = new StartBox();
        box1.setIdBox(1);
        boxes.add(box1);
        PropertyBox box2 = new ProvinceProperty();
        box2.setIdBox(2);
        boxes.add(box2);
        Dashboard dashboard = Dashboard.getInstance();
        dashboard.setBoxes(boxes);


        dashboardService.setPlayersIntoTheirBoxes(players);

        assertEquals(1, box1.getPlayers().size());
        assertEquals(1, box2.getPlayers().size());
    }
    @Test
    public void createInitialDashboard() {
        List<AbstractBox> boxes = new ArrayList<>();
        CardBox destinyBox = new CardBox();
        destinyBox.setDescription("Destiny Box");
        CardBox luckyBox = new CardBox();
        luckyBox.setDescription("Lucky Box");
        boxes.add(destinyBox);
        boxes.add(luckyBox);
        when(boxService.getBoxes()).thenReturn(boxes);

        LinkedList<AbstractCard> cards = new LinkedList<>();
        MovementQuantityCard destinyCard1 = new MovementQuantityCard();
        destinyCard1.setIsDestiny(true);
        MovementBoxCard destinyCard2 = new MovementBoxCard();
        destinyCard2.setIsDestiny(true);
        PaymentCard luckyCard1 = new PaymentCard();
        luckyCard1.setIsDestiny(false);
        PaymentCard luckyCard2 = new PaymentCard();
        luckyCard2.setIsDestiny(false);
        cards.add(destinyCard1);
        cards.add(destinyCard2);
        cards.add(luckyCard1);
        cards.add(luckyCard2);
        when(cardService.getCards()).thenReturn(cards);

        dashboardService.createInitialDashboard();

        assertEquals(2, Dashboard.getInstance().getBoxes().size());
        assertEquals(2, Dashboard.getInstance().getDestinyCards().getCards().size());
        assertEquals(2, Dashboard.getInstance().getLuckyCards().getCards().size());
        assertTrue(destinyBox.getIsDestiny());
        assertFalse(luckyBox.getIsDestiny());

        verify(boxService).getBoxes();
        verify(cardService).getCards();
    }
    @Test
    void getInstance(){
        DashboardServiceImpl dashboard1 = DashboardServiceImpl.getInstance(boxService,cardService);
        DashboardServiceImpl dashboard2 = DashboardServiceImpl.getInstance(boxService,cardService);

        assertSame(dashboard1,dashboard2);
    }
}
