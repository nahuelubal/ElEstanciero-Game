package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.model.config.Color;
import ar.edu.utn.frc.tup.lciii.model.config.GameStyle;
import ar.edu.utn.frc.tup.lciii.model.deeds.AbstractDeed;
import ar.edu.utn.frc.tup.lciii.model.deeds.DeedType;
import ar.edu.utn.frc.tup.lciii.model.deeds.Province;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ConservativeBotPlayerTest {

	@Mock
	AbstractDeed mockDeed;
	@Mock
	Province mockProvince;

	GameStyle gameStyle;
	@InjectMocks
	ConservativeBotPlayer botConservative;

	@BeforeEach
	public void setUp() {
		mockDeed = mock(AbstractDeed.class);
		mockProvince = mock(Province.class);
		gameStyle = new GameStyle(1,BotPlayerTypes.CONSERVATIVE,false,false,20,new ArrayList<>());
		botConservative = new ConservativeBotPlayer();
		botConservative.setColor(Color.ORANGE);
		botConservative.setGameStyle(gameStyle);

		when(mockProvince.getDeedType()).thenReturn(DeedType.PROVINCE);
		when(mockProvince.getName()).thenReturn("FORMOSA SUR");
		botConservative.gameStyle.setProvincesToPrioritize(Collections.singletonList(mockProvince));
	}

	@Test
	void buyPropertyTest() {
		List<Province>provinces = new ArrayList<>();
		botConservative.setMoneyAvailable(3000);
		when(mockProvince.getPurchaseValue()).thenReturn(1000);
		provinces.add(mockProvince);

		Province mockDeed1 = mock(Province.class);
		when(mockDeed1.getPurchaseValue()).thenReturn(1000);
		when(mockDeed1.getName()).thenReturn("FORMOSA SUR");
		when(mockDeed1.getDeedType()).thenReturn(DeedType.PROVINCE);
		provinces.add(mockDeed1);

		botConservative.getGameStyle().setProvincesToPrioritize(provinces);


		botConservative.buyProperty(mockProvince);
		botConservative.buyProperty(mockDeed1);

		List<AbstractDeed> buyList = new ArrayList<>();
		buyList.add(mockProvince);
		buyList.add(mockDeed1);

		assertEquals(buyList, botConservative.deeds);
	}

	@Test
	void dontBuyPropertyTest() {
		botConservative.setMoneyAvailable(3000);
		when(mockProvince.getPurchaseValue()).thenReturn(1000);

		AbstractDeed mockDeed1 = mock(AbstractDeed.class);
		when(mockDeed1.getPurchaseValue()).thenReturn(1000);
		when(mockDeed1.getName()).thenReturn("BUENOS AIRES CENTRO");

		botConservative.buyProperty(mockProvince);
		botConservative.buyProperty(mockDeed1);

		List<AbstractDeed> buyList = new ArrayList<>();
		buyList.add(mockProvince);
		buyList.add(mockDeed1);

		assertNotEquals(buyList, botConservative.deeds);

	}

	/**
	 * Este metodo permite manejar la probabilidad de que cada 4 provincias compradas,
	 * la quinta comprada PUEDA ser una que no sea prioritaria
	 */
	@Test
	void buyPropertyWithFiveOrMoreDeeds() {
		List<Province>provinces = new ArrayList<>();
		botConservative.setMoneyAvailable(10000);
		when(mockProvince.getPurchaseValue()).thenReturn(1000);
		provinces.add(mockProvince);



		Province mockProvince1 = mock(Province.class);
		when(mockProvince1.getPurchaseValue()).thenReturn(1000);
		when(mockProvince1.getName()).thenReturn("FORMOSA SUR");
		when(mockProvince1.getDeedType()).thenReturn(DeedType.PROVINCE);
		provinces.add(mockProvince1);


		Province mockProvince2 = mock(Province.class);
		when(mockProvince2.getPurchaseValue()).thenReturn(1000);
		when(mockProvince2.getName()).thenReturn("FORMOSA NORTE");
		when(mockProvince2.getDeedType()).thenReturn(DeedType.PROVINCE);
		provinces.add(mockProvince2);

		Province mockProvince3 = mock(Province.class);
		when(mockProvince3.getPurchaseValue()).thenReturn(1000);
		when(mockProvince3.getName()).thenReturn("SALTA NORTE");
		when(mockProvince3.getDeedType()).thenReturn(DeedType.PROVINCE);
		provinces.add(mockProvince3);

		Province mockProvince4 = mock(Province.class);
		when(mockProvince4.getPurchaseValue()).thenReturn(1000);
		when(mockProvince4.getName()).thenReturn("SALTA SUR");
		when(mockProvince4.getDeedType()).thenReturn(DeedType.PROVINCE);
		provinces.add(mockProvince4);

		Province mockProvince5 = mock(Province.class);
		when(mockProvince5.getPurchaseValue()).thenReturn(1000);
		when(mockProvince5.getName()).thenReturn("CORDOBA SUR");
		when(mockProvince5.getDeedType()).thenReturn(DeedType.PROVINCE);

		botConservative.getGameStyle().setProvincesToPrioritize(provinces);


		botConservative.buyProperty(mockProvince);
		botConservative.buyProperty(mockProvince1);
		botConservative.buyProperty(mockProvince2);
		botConservative.buyProperty(mockProvince3);
		botConservative.buyProperty(mockProvince4);
		botConservative.buyProperty(mockProvince5);

		List<AbstractDeed> buyList = new ArrayList<>();
		buyList.add(mockProvince);
		buyList.add(mockProvince1);
		buyList.add(mockProvince2);
		buyList.add(mockProvince3);
		buyList.add(mockProvince4);
		buyList.add(mockProvince5);

		assertEquals(buyList, botConservative.deeds);
	}

	@Test
	void payTest() {
		botConservative.setMoneyAvailable(10000);
		botConservative.pay(2000);
		assertEquals(8000, botConservative.getMoneyAvailable());
	}

	@Test
	void payDoesntHaveEnoughMoneyTest() {
		ConservativeBotPlayer conservativeSpy = spy(new ConservativeBotPlayer());
		GameStyle gameStyle = new GameStyle(1, BotPlayerTypes.CONSERVATIVE, false, false, 20, new ArrayList<>());

		conservativeSpy.setMoneyAvailable(9000);
		conservativeSpy.setDeeds(new ArrayList<>());
		conservativeSpy.getDeeds().add(mockDeed);
		conservativeSpy.setGameStyle(gameStyle);

		when(mockDeed.getMortgageValue()).thenReturn(5000);
		when(mockDeed.getName()).thenReturn("FORMOSA SUR");
		when(mockDeed.getDeedType()).thenReturn(DeedType.PROVINCE);

		conservativeSpy.pay(10000);

		verify(conservativeSpy, times(1)).mortgageProperty(mockDeed);
		assertEquals(4000, conservativeSpy.getMoneyAvailable());
	}

	@Test
	void receiveTest() {
		botConservative.setMoneyAvailable(10000);
		botConservative.receive(2000);
		assertEquals(12000, botConservative.getMoneyAvailable());
	}

	@Test
	void mortgagePropertyTest() {
		botConservative.setMoneyAvailable(10000);
		botConservative.getDeeds().add(mockDeed);

		//El name esta unicamente para que no aparezca Property null en el print de consola
		when(mockDeed.getName()).thenReturn("FORMOSA CENTRO");
		when(mockDeed.getMortgageValue()).thenReturn(5000);

		botConservative.mortgageProperty(mockDeed);

		assertEquals(15000, botConservative.getMoneyAvailable());
	}

	@Test
	void addRanch() {
		botConservative.setMoneyAvailable(10000);
		Province provinceDeed = spy(Province.class);

		when(provinceDeed.getConstructionValue()).thenReturn(500);

		botConservative.addRanch(provinceDeed);

		assertEquals(7500, botConservative.getMoneyAvailable());
		assertTrue(provinceDeed.getHasRanch());
	}

	@Test
	void addFarm() {
		botConservative.setMoneyAvailable(5000);

		Province provinceDeed = spy(Province.class);

		when(provinceDeed.getConstructionValue()).thenReturn(500);

		botConservative.addFarm(provinceDeed, 2);

		assertEquals(4000, botConservative.getMoneyAvailable());
		assertEquals(2, provinceDeed.getFarmQuantity());
	}
}