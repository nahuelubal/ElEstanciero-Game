package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.model.auth.Auth;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import ar.edu.utn.frc.tup.lciii.model.deeds.Zone;
import ar.edu.utn.frc.tup.lciii.services.impl.DeedServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HumanPlayerTest {
    HumanPlayer humanPlayer;
    Scanner scanner;
    AbstractDeed mockDeed;

    @BeforeEach
    void setUp() {
        humanPlayer = spy(HumanPlayer.class);
        humanPlayer.setMoneyAvailable(2500);
        humanPlayer.deeds = new ArrayList<>();
        mockDeed = mock(AbstractDeed.class);
        scanner = mock(Scanner.class);
    }

    @Test
    void buyProperty() {
        when(mockDeed.getPurchaseValue()).thenReturn(1000);
        humanPlayer.buyProperty(mockDeed);

        verify(humanPlayer, times(1)).pay(1000);
        assertTrue(humanPlayer.deeds.contains(mockDeed));
        verify(mockDeed, times(1)).setIsPurchased(true);
    }

    @Test
    void choosePropertyToMortgageTest() {
        humanPlayer.setScanner(scanner);

        AbstractDeed province = new Province();
        province.setName("CORDOBA CENTRO");
        province.setMortgageValue(5000);
        province.setIsMortgaged(false);

        AbstractDeed province1 = new Province();
        province1.setName("JUJUY CENTRO");
        province1.setMortgageValue(2500);
        province1.setIsMortgaged(false);

        ArrayList<AbstractDeed> deeds = new ArrayList<>();
        deeds.add(province);
        deeds.add(province1);
        humanPlayer.setDeeds(deeds);

        doNothing().when(humanPlayer).mortgageProperty(any(AbstractDeed.class));
        when(scanner.nextLine()).thenReturn("1");

        humanPlayer.choosePropertyToMortgage();
        verify(humanPlayer, times(1)).mortgageProperty(province);

    }

    @Test
    void mortgagedPropertyTest() {
        when(mockDeed.getName()).thenReturn("SALTA SUR");
        when(mockDeed.getMortgageValue()).thenReturn(5000);
        when(mockDeed.getIsMortgaged()).thenReturn(false);

        humanPlayer.deeds.add(mockDeed);

        humanPlayer.mortgageProperty(mockDeed);

        verify(mockDeed, times(1)).setIsMortgaged(true);
        verify(humanPlayer, times(1)).receive(5000);

        assertFalse(mockDeed.getIsMortgaged());
    }

    @Test
    void selectOption1Test() {
        when(scanner.nextLine()).thenReturn("1"); // esto simula la entrada del usuario
        humanPlayer.setScanner(scanner);
        doNothing().when(humanPlayer).choosePropertyToMortgage();
        humanPlayer.selectOption();
        verify(humanPlayer, times(1)).choosePropertyToMortgage();
    }

    @Test
    void addRanch() {
        humanPlayer.setMoneyAvailable(10000);
        Province provinceDeed = spy(Province.class);

        when(provinceDeed.getConstructionValue()).thenReturn(500);

        humanPlayer.addRanch(provinceDeed);

        assertEquals(7500, humanPlayer.getMoneyAvailable());
        assertTrue(provinceDeed.getHasRanch());
    }

    @Test
    void addFarm() {
        humanPlayer.setMoneyAvailable(5000);

        Province provinceDeed = spy(Province.class);

        when(provinceDeed.getConstructionValue()).thenReturn(500);

        humanPlayer.addFarm(provinceDeed, 2);

        assertEquals(4000, humanPlayer.getMoneyAvailable());
        assertEquals(2, provinceDeed.getFarmQuantity());
    }

    @Test
    void payTests() {
        humanPlayer.pay(2000);
        assertEquals(500, humanPlayer.getMoneyAvailable());
    }

    @Test
    void receiveTest() {
        humanPlayer.receive(5000);
        assertEquals(7500, humanPlayer.getMoneyAvailable());
    }

    @Test
    void setBankRupt() {
        try (MockedStatic<Game> gameMockedStatic = Mockito.mockStatic(Game.class)) {
            Game game = mock(Game.class);
            doNothing().when(game).removePlayer(humanPlayer);
            gameMockedStatic.when(Game::getInstance).thenReturn(game);
            Province province = new Province();
            province.setIsPurchased(true);
            province.setIsMortgaged(true);
            province.setHasRanch(true);

            List<AbstractDeed> deeds = new ArrayList<>();
            deeds.add(province);

            humanPlayer.setDeeds(deeds);
            humanPlayer.setBankrupt();

            assertEquals(false, province.getIsPurchased());
            assertEquals(false, province.getIsMortgaged());
            assertTrue(humanPlayer.deeds.isEmpty());
        }

    }
    @Test
    void hasEntireProvinceTest() {
        try (MockedStatic<DeedServiceImpl> deedServiceMockedStatic = Mockito.mockStatic(DeedServiceImpl.class)) {
            DeedServiceImpl deedService = mock(DeedServiceImpl.class);
            Province province = new Province();

            province.setZone(new Zone(1, ""));

            List<AbstractDeed> deeds = new ArrayList<>();
            deeds.add(province);
            when(deedService.getDeedList()).thenReturn(deeds);

            deedServiceMockedStatic.when(() -> DeedServiceImpl.getInstance(any(), any()))
                    .thenReturn(deedService);

            humanPlayer.setDeeds(deeds);
            assertTrue(humanPlayer.hasEntireProvince(province));

        }

    }

    @Test
    void calculateRanchValueTest() {
        Province province = new Province();
        province.setFarmQuantity(1);
        province.setConstructionValue(1000);

        assertEquals(4000, humanPlayer.calculateRanchValue(province));

    }

    @Test
    void hasPropertyTest() {
        Province province = new Province();
        province.setName("province test");

        List<AbstractDeed> deeds = new ArrayList<>();
        deeds.add(province);

        humanPlayer.setDeeds(deeds);
        assertTrue(humanPlayer.hasProperty(province));

    }

    @Test
    void getPropertyTypeQuantity() {
        Province province = new Province();
        province.setName("province test");

        List<AbstractDeed> deeds = new ArrayList<>();
        deeds.add(province);
        deeds.add(province);

        humanPlayer.setDeeds(deeds);
        assertEquals(2, humanPlayer.getPropertyTypeQuantity(province));

    }
}