package ar.edu.utn.frc.tup.lciii.model.deeds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    Company company;
    @BeforeEach
    public void setUp(){
        company = new Company(1, "", 1, 100, false, false,
                1, "", 100,200,300, DeedType.COMPANY);
    }

    @Test
    void getRent() {
        int result = company.getRent(2,8);
        assertEquals(result,1600);
    }
    @Test
    void getRentFalse() {
        int result = company.getRent(1,10);
        assertNotEquals(result,1600);
    }
}