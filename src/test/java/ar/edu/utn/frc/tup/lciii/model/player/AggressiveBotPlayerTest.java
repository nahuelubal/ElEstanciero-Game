package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.Game;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.DeedType;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AggressiveBotPlayerTest {

    @Mock
    Game game;
    @Mock
    AbstractDeed mockDeed;
    @Mock
    AbstractPlayer mockPlayer;

    GameStyle gameStyle;
    @Mock
    Province mockProvince;
    @InjectMocks
    AggressiveBotPlayer botAggressive;

    @BeforeEach
    public void setUp() {
        game = mock(Game.class);
        mockProvince = mock(Province.class);
        when(mockProvince.getDeedType()).thenReturn(DeedType.PROVINCE);
        when(mockProvince.getName()).thenReturn("CORDOBA CENTRO");

        gameStyle = new GameStyle(1 , BotPlayerTypes.AGGRESSIVE, true ,
                true,100 , Collections.singletonList(mockProvince) );
        mockDeed = mock(AbstractDeed.class);
        mockPlayer = mock(AbstractPlayer.class);

        botAggressive = spy(new AggressiveBotPlayer());
        botAggressive.setMoneyAvailable(7500);
        botAggressive.setGame(game);
        botAggressive.setColor(Color.GREEN);
        botAggressive.setGameStyle(gameStyle);
        botAggressive.deeds = new ArrayList<>();
    }

    @Test
    void buyPropertyTest() {
        when(mockProvince.getPurchaseValue()).thenReturn(3000);
        botAggressive.buyProperty(mockProvince);

        List<AbstractDeed> buyList = new ArrayList<>();
        buyList.add(mockProvince);

        assertEquals(buyList, botAggressive.getDeeds());
        assertEquals(4500 , botAggressive.getMoneyAvailable());
    }

    /**
     * en este caso el bot solo comprara una provincia
     * ya que LA RIOJA SUR no es de su prioridad
     */
    @Test
    void dontBuyPropertyTest() {
        Province mockProvince1 = mock(Province.class);
        when(mockProvince1.getPurchaseValue()).thenReturn(1000);
        when(mockProvince1.getName()).thenReturn("LA RIOJA SUR");
        when(mockProvince1.getDeedType()).thenReturn(DeedType.PROVINCE);

        Queue<AbstractPlayer> players = new LinkedList<>();
        players.add(mockPlayer);

        when(game.getPlayers()).thenReturn(players);
        when(mockPlayer.getDeeds()).thenReturn(new ArrayList<>());

        botAggressive.buyProperty(mockProvince1);

        assertEquals(0 , botAggressive.deeds.size());
    }

    @Test
    @Tag("lostAProperty")
    void lostAProperty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        AbstractPlayer humanPlayer = new HumanPlayer();
        humanPlayer.setIdPlayer(1);
        ArrayList<AbstractDeed> humanDeeds = new ArrayList<>();
        humanDeeds.add(mockProvince);
        humanPlayer.setDeeds(humanDeeds);

        Queue<AbstractPlayer> playerList = new LinkedList<>();
        playerList.add(humanPlayer);

        when(botAggressive.getGame().getPlayers()).thenReturn((playerList));

        Method method = AggressiveBotPlayer.class.getDeclaredMethod("lostAProperty");
        method.setAccessible(true);

        boolean respuesta = (boolean) method.invoke(botAggressive);
        assertTrue(respuesta);
    }

    @Test
    @Tag("lostAProperty")
    void DontlostAProperty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Province mockProvince1 = mock(Province.class);
        when(mockProvince1.getName()).thenReturn("JUJUY NORTE");
        when(mockProvince1.getDeedType()).thenReturn(DeedType.PROVINCE);
        when(mockProvince1.getPurchaseValue()).thenReturn(1000);

        AbstractPlayer humanPlayer = new HumanPlayer();
        humanPlayer.setIdPlayer(1);
        ArrayList<AbstractDeed> humanDeeds = new ArrayList<>();
        humanDeeds.add(mockProvince1);
        humanPlayer.setDeeds(humanDeeds);

        Queue<AbstractPlayer> playerList = new LinkedList<>();
        playerList.add(humanPlayer);
        when(botAggressive.getGame().getPlayers()).thenReturn((playerList));

        Method method = AggressiveBotPlayer.class.getDeclaredMethod("lostAProperty");
        method.setAccessible(true);

        assertFalse((boolean) method.invoke(botAggressive));
    }

    @Test
    void boughtAllPriorityPropertiesTest() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        // Obtener la lista existente de propiedades prioritarias
        List<Province> existingProvinces = botAggressive.getGameStyle().getProvincesToPrioritize();
        List<AbstractDeed>deedsBotAgressive = new ArrayList<>();
        deedsBotAgressive.add(mockProvince);

        List<Province> provinciasPrioritarias = new ArrayList<>(existingProvinces);


        Province mockProvince1 = mock(Province.class);
        when(mockProvince1.getName()).thenReturn("CORDÓBA SUR");
        provinciasPrioritarias.add(mockProvince1);
        deedsBotAgressive.add(mockProvince1);

        Province mockProvince2 = mock(Province.class);
        when(mockProvince2.getName()).thenReturn("CORDÓBA NORTE");
        provinciasPrioritarias.add(mockProvince2);
        deedsBotAgressive.add(mockProvince2);

        Province mockProvince3 = mock(Province.class);
        when(mockProvince3.getName()).thenReturn("TUCUMAN NORTE");
        provinciasPrioritarias.add(mockProvince3);
        deedsBotAgressive.add(mockProvince3);


        Province mockProvince4 = mock(Province.class);
        when(mockProvince4.getName()).thenReturn("TUCUMAN SUR");
        provinciasPrioritarias.add(mockProvince4);
        deedsBotAgressive.add(mockProvince4);


        Province mockProvince5 = mock(Province.class);
        when(mockProvince5.getName()).thenReturn("TUCUMAN CENTRO");
        provinciasPrioritarias.add(mockProvince5);
        deedsBotAgressive.add(mockProvince5);

        Province mockProvince6 = mock(Province.class);
        when(mockProvince6.getName()).thenReturn("BUENOS AIRES NORTE");
        provinciasPrioritarias.add(mockProvince6);
        deedsBotAgressive.add(mockProvince6);


        Province mockProvince7 = mock(Province.class);
        when(mockProvince7.getName()).thenReturn("BUENOS AIRES CENTRO");
        provinciasPrioritarias.add(mockProvince7);
        deedsBotAgressive.add(mockProvince7);


        Province mockProvince8 = mock(Province.class);
        when(mockProvince8.getName()).thenReturn("BUENOS AIRES SUR");
        provinciasPrioritarias.add(mockProvince8);
        deedsBotAgressive.add(mockProvince8);


        // Actualizar la lista en el gameStyle
        botAggressive.getGameStyle().setProvincesToPrioritize(provinciasPrioritarias);
        botAggressive.setDeeds(deedsBotAgressive);


        Method method = AggressiveBotPlayer.class.getDeclaredMethod("boughtAllPriorityProperties");
        method.setAccessible(true);

        assertTrue((Boolean) method.invoke(botAggressive));

    }



    @Test
    void addFarmTest() {
        when(mockProvince.getFarmQuantity()).thenReturn(0);
        when(mockProvince.getConstructionValue()).thenReturn(200);

        botAggressive.deeds.add(mockProvince);

        botAggressive.addFarm(mockProvince, 2);
        verify(botAggressive , times(1)).pay(400);
        verify(mockProvince , times(1)).setFarmQuantity(2);
    }

    @Test
    void payTest() {
        botAggressive.pay(2000);
        assertEquals(5500, botAggressive.getMoneyAvailable());
    }

    @Test
    void addRanchTest(){
      Province province = mock(Province.class);
      when(province.getHasRanch()).thenReturn(false);
      when(province.getFarmQuantity()).thenReturn(2);
      when(province.getConstructionValue()).thenReturn(100);

      botAggressive.addRanch(province);
      verify(botAggressive , times(1)).calculateRanchValue(province);
      verify(province , times(1)).setHasRanch(true);

    }
    @Test
    void payTestWithInsufficientMoney() throws Exception {

        AggressiveBotPlayer aggressiveBotPlayer = spy(new AggressiveBotPlayer());
        GameStyle gameStyle = new GameStyle(1, BotPlayerTypes.AGGRESSIVE, true, true, 20, new ArrayList<>());

        aggressiveBotPlayer.setMoneyAvailable(9000);
        aggressiveBotPlayer.setDeeds(new ArrayList<>());
        aggressiveBotPlayer.getDeeds().add(mockDeed);
        aggressiveBotPlayer.setGameStyle(gameStyle);

        when(mockDeed.getMortgageValue()).thenReturn(5000);
        when(mockDeed.getName()).thenReturn("FORMOSA SUR");
        when(mockDeed.getDeedType()).thenReturn(DeedType.PROVINCE);

        aggressiveBotPlayer.pay(10000);

        verify(aggressiveBotPlayer, times(1)).mortgageProperty(mockDeed);
        assertEquals(4000, aggressiveBotPlayer.getMoneyAvailable());
    }

    @Test
    void receiveTest() {
        botAggressive.receive(2000);
        assertEquals(9500, botAggressive.getMoneyAvailable());
    }

    @Test
    void mortgagePropertyTest(){
      when(mockDeed.getName()).thenReturn("JUJUY NORTE");
      when(mockDeed.getMortgageValue()).thenReturn(2000);
      when(mockDeed.getIsMortgaged()).thenReturn(false);

      botAggressive.mortgageProperty(mockDeed);

        verify(mockDeed , times(1)).setIsMortgaged(true);
        verify(mockDeed , times(1)).getMortgageValue();
        verify(botAggressive).receive(2000);
    }


    @Test
    void choosePropertyToMortgageTest() {

        AbstractDeed mockDeed1 = mock(AbstractDeed.class);
        when(mockDeed1.getName()).thenReturn("JUJUY NORTE");
        when(mockDeed1.getIsMortgaged()).thenReturn(false);


        gameStyle = new GameStyle(1 , BotPlayerTypes.AGGRESSIVE, true ,
                true,100 , Collections.singletonList(mockProvince) );

        ArrayList<AbstractDeed> deeds = new ArrayList<>();
        deeds.add(mockDeed1);

        AggressiveBotPlayer botAggressive = spy(new AggressiveBotPlayer());
        botAggressive.setGameStyle(gameStyle);
        botAggressive.setDeeds(deeds);
        botAggressive.setColor(Color.GREEN);
        botAggressive.setGame(game);

        doNothing().when(botAggressive).mortgageProperty(any(AbstractDeed.class));

        botAggressive.choosePropertyToMortgage();

        verify(botAggressive, times(1)).mortgageProperty(mockDeed1);
    }
}