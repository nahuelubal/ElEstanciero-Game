package ar.edu.utn.frc.tup.lciii.model.deeds;

import ar.edu.utn.frc.tup.lciii.model.box.BoxType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeedTypeTest {

	@Test
	void enumValues(){
		DeedType[] expectedValues = {DeedType.COMPANY , DeedType.PROVINCE , DeedType.RAILWAY};

		assertArrayEquals(DeedType.values(),expectedValues );
	}

	@Test
	void valueOf() {
		assertEquals(DeedType.COMPANY , DeedType.valueOf("COMPANY"));
		assertEquals(DeedType.PROVINCE , DeedType.valueOf("PROVINCE"));
		assertEquals(DeedType.RAILWAY , DeedType.valueOf("RAILWAY"));
	}
}