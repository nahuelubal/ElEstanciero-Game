package ar.edu.utn.frc.tup.lciii.model.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void constructorSinArgsTest(){
        User newUser = new User();
        assertNotNull(newUser);
    }

    @Test
    void constructorConArgsTest(){
        User newUser = new User("jorge" , "jorge123456");
        assertEquals("jorge" , newUser.getUserName());
        assertEquals("jorge123456" , newUser.getPassword());
    }
}