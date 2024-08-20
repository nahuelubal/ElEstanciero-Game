package ar.edu.utn.frc.tup.lciii.model.player;
import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.DeedType;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WellBalancedBotPlayerTest {

    @Mock
    Game game;
    @Mock
    AbstractDeed mockDeed;
    @Mock
    Province mockProvince;

    GameStyle gameStyle;
    @InjectMocks
    WellBalancedBotPlayer botWellBalanced;
    @BeforeEach
    public void beforeEach(){
        botWellBalanced = new WellBalancedBotPlayer();
        game = mock(Game.class);
        gameStyle = new GameStyle(1,BotPlayerTypes.WELL_BALANCED,false,true,33,new ArrayList<>());
        botWellBalanced.setGame(game);
        botWellBalanced.setColor(Color.ORANGE);
        botWellBalanced.setGameStyle(gameStyle);
        mockDeed = mock(AbstractDeed.class);
        mockProvince = mock(Province.class);
        when(mockProvince.getDeedType()).thenReturn(DeedType.PROVINCE);
        when(mockProvince.getName()).thenReturn("MENDOZA SUR");
        botWellBalanced.gameStyle.setProvincesToPrioritize(Collections.singletonList(mockProvince));
    }


    @Test
    void buyProperty() {
        botWellBalanced.setMoneyAvailable(3000);
        when(mockProvince.getPurchaseValue()).thenReturn(1000);

        botWellBalanced.buyProperty(mockProvince);

        List<AbstractDeed> buyList = new ArrayList<>();
        buyList.add(mockProvince);

        assertTrue(botWellBalanced.getDeeds().contains(mockProvince));
        verify(mockProvince).setIsPurchased(true);
        assertEquals(2000, botWellBalanced.getMoneyAvailable());
        assertEquals(botWellBalanced.getDeeds(),buyList);
    }
    @Test
    void dontBuyPropertyTest() {
        botWellBalanced.setMoneyAvailable(3000);
        when(mockProvince.getPurchaseValue()).thenReturn(1000);

        AbstractDeed mockDeed1 = mock(AbstractDeed.class);
        when(mockDeed1.getPurchaseValue()).thenReturn(1000);
        when(mockDeed1.getDeedType()).thenReturn(DeedType.PROVINCE);
        botWellBalanced.buyProperty(mockProvince);
        botWellBalanced.buyProperty(mockDeed1);

        List<AbstractDeed> buyList = new ArrayList<>();
        buyList.add(mockProvince);
        buyList.add(mockDeed1);

        assertNotEquals(buyList, botWellBalanced.deeds);

    }
    @Test
    void buyPropertyWithThreeOrMoreDeeds() {
        List<Province>provinces = new ArrayList<>();
        botWellBalanced.setMoneyAvailable(10000);
        when(mockProvince.getPurchaseValue()).thenReturn(1000);
        provinces.add(mockProvince);

        Province mockProvince1 = mock(Province.class);
        when(mockProvince1.getPurchaseValue()).thenReturn(1000);
        when(mockProvince1.getName()).thenReturn("MENDOZA NORTE");
        when(mockProvince1.getDeedType()).thenReturn(DeedType.PROVINCE);
        provinces.add(mockProvince1);

        Province mockProvince2 = mock(Province.class);
        when(mockProvince2.getPurchaseValue()).thenReturn(1000);
        when(mockProvince2.getName()).thenReturn("MENDOZA CENTRO");
        when(mockProvince2.getDeedType()).thenReturn(DeedType.PROVINCE);
        provinces.add(mockProvince2);


        Province mockProvince3 = mock(Province.class);
        when(mockProvince3.getPurchaseValue()).thenReturn(1000);
        when(mockProvince3.getName()).thenReturn("SALTA SUR");
        when(mockProvince3.getDeedType()).thenReturn(DeedType.PROVINCE);


        botWellBalanced.getGameStyle().setProvincesToPrioritize(provinces);

        botWellBalanced.buyProperty(mockProvince);
        botWellBalanced.buyProperty(mockProvince1);
        botWellBalanced.buyProperty(mockProvince2);
        botWellBalanced.buyProperty(mockProvince3);

        List<AbstractDeed> buyList = new ArrayList<>();
        buyList.add(mockProvince);
        buyList.add(mockProvince1);
        buyList.add(mockProvince2);
        buyList.add(mockProvince3);

        assertEquals(buyList, botWellBalanced.deeds);
    }
    @Test
    void addRanch() {
        botWellBalanced.setMoneyAvailable(5000);
        Province provinceDeed = spy(Province.class);

        when(provinceDeed.getConstructionValue()).thenReturn(500);

        botWellBalanced.addRanch(provinceDeed);

        assertEquals(2500, botWellBalanced.getMoneyAvailable());
        assertTrue(provinceDeed.getHasRanch());
    }

    @Test
    void addFarm() {
        botWellBalanced.setMoneyAvailable(5000);
        Province provinceDeed = spy(Province.class);
        when(provinceDeed.getConstructionValue()).thenReturn(500);
        botWellBalanced.addFarm(provinceDeed, 4);
        assertEquals(3000, botWellBalanced.getMoneyAvailable());
        assertEquals(4, provinceDeed.getFarmQuantity());
    }

    @Test
    void receive() {
        botWellBalanced.setMoneyAvailable(10000);
        botWellBalanced.receive(2000);
        assertEquals(12000, botWellBalanced.getMoneyAvailable());
    }

    @Test
    void mortgageProperty() {
        botWellBalanced.setMoneyAvailable(10000);
        botWellBalanced.getDeeds().add(mockDeed);

        //El name esta unicamente para que no aparezca Property null en el print de consola
        when(mockDeed.getName()).thenReturn("MENDOZA SUR");
        when(mockDeed.getMortgageValue()).thenReturn(5000);

        botWellBalanced.mortgageProperty(mockDeed);

        assertEquals(15000, botWellBalanced.getMoneyAvailable());
    }

    @Test
    void pay() {
        botWellBalanced.setMoneyAvailable(10000);
        botWellBalanced.pay(2000);
        assertEquals(8000, botWellBalanced.getMoneyAvailable());

    }
    @Test
    void payDoesntHaveEnoughMoneyTest() {
        WellBalancedBotPlayer conservativeSpy = spy(new WellBalancedBotPlayer());
        GameStyle gameStyle = new GameStyle(1, BotPlayerTypes.WELL_BALANCED, false, true, 33, new ArrayList<>());

        conservativeSpy.setMoneyAvailable(9000);
        conservativeSpy.setDeeds(new ArrayList<>());
        conservativeSpy.getDeeds().add(mockDeed);
        conservativeSpy.setGameStyle(gameStyle);

        when(mockDeed.getMortgageValue()).thenReturn(5000);
        when(mockDeed.getName()).thenReturn("MENDOZA SUR");
        when(mockDeed.getDeedType()).thenReturn(DeedType.PROVINCE);

        conservativeSpy.pay(10000);

        verify(conservativeSpy, times(1)).mortgageProperty(mockDeed);
        assertEquals(4000, conservativeSpy.getMoneyAvailable());
    }
    @Test
    void testMoreThan75Sell() {
        AbstractPlayer mockPlayer1 = Mockito.mock(AbstractPlayer.class);
        AbstractPlayer mockPlayer2 = Mockito.mock(AbstractPlayer.class);
        AbstractDeed mockDeed1 = Mockito.mock(AbstractDeed.class);
        AbstractDeed mockDeed2 = Mockito.mock(AbstractDeed.class);
        AbstractDeed mockDeed3 = Mockito.mock(AbstractDeed.class);

        when(mockDeed1.getIsPurchased()).thenReturn(true);
        when(mockDeed2.getIsPurchased()).thenReturn(false);
        when(mockDeed3.getIsPurchased()).thenReturn(false);

        when(mockPlayer1.getDeeds()).thenReturn(Arrays.asList(mockDeed1, mockDeed2));
        when(mockPlayer2.getDeeds()).thenReturn(Collections.singletonList(mockDeed3));
        Queue<AbstractPlayer> players = new LinkedList<>();
        players.add(mockPlayer1);
        players.add(mockPlayer2);
        when(game.getPlayers()).thenReturn(players);

        assertFalse(botWellBalanced.moreThan75Sell());
    }
    @Test
    void testMoreThan75SellV2() {
        AbstractPlayer mockPlayer1 = Mockito.mock(AbstractPlayer.class);
        AbstractPlayer mockPlayer2 = Mockito.mock(AbstractPlayer.class);
        AbstractDeed mockDeed1 = Mockito.mock(AbstractDeed.class);
        AbstractDeed mockDeed2 = Mockito.mock(AbstractDeed.class);
        AbstractDeed mockDeed3 = Mockito.mock(AbstractDeed.class);

        when(mockDeed1.getIsPurchased()).thenReturn(true);
        when(mockDeed2.getIsPurchased()).thenReturn(true);
        when(mockDeed3.getIsPurchased()).thenReturn(true);

        when(mockPlayer1.getDeeds()).thenReturn(Arrays.asList(mockDeed1, mockDeed2));
        when(mockPlayer2.getDeeds()).thenReturn(Collections.singletonList(mockDeed3));
        Queue<AbstractPlayer> players = new LinkedList<>();
        players.add(mockPlayer1);
        players.add(mockPlayer2);
        when(game.getPlayers()).thenReturn(players);

        assertTrue(botWellBalanced.moreThan75Sell());
    }
}