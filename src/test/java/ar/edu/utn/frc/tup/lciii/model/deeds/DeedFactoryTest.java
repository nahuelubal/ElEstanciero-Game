package ar.edu.utn.frc.tup.lciii.model.deeds;

import ar.edu.utn.frc.tup.lciii.services.impl.DeedFactoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeedFactoryTest {

	private final DeedFactoryImpl deedFactory = new ConcreteDeedFactory();

	@Test
	void testCreateDeedCompany() {
		AbstractDeed deed = deedFactory.createDeed(DeedType.COMPANY);
		assertInstanceOf(Company.class,deed);
	}

	@Test
	void testCreateDeedProvince() {
		AbstractDeed deed = deedFactory.createDeed(DeedType.PROVINCE);
		assertInstanceOf(Province.class,deed);
	}

	@Test
	void testCreateDeedRailway() {
		AbstractDeed deed = deedFactory.createDeed(DeedType.RAILWAY);
		assertInstanceOf(Railway.class,deed);
	}
}