package ar.edu.utn.frc.tup.lciii.others.dependency_injection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAbstractDependencyInjector {
    private AbstractDependencyInjector dependencyInjector;

    @BeforeEach
    void setup() {
        dependencyInjector = new AbstractDependencyInjector();
    }

    @Test
    void testRepositories() {
        assertNotNull(dependencyInjector.getUserRepository());
        assertNotNull(dependencyInjector.getPlayerRepository());
        assertNotNull(dependencyInjector.getGameRepository());
        assertNotNull(dependencyInjector.getBoxRepository());
        assertNotNull(dependencyInjector.getCardRepository());
        assertNotNull(dependencyInjector.getDeedRepository());
        assertNotNull(dependencyInjector.getTaxRepository());
    }

    @Test
    void testServices() {
        assertNotNull(dependencyInjector.getPlayerService());
        assertNotNull(dependencyInjector.getGameService());
        assertNotNull(dependencyInjector.getBoxService());
        assertNotNull(dependencyInjector.getCardService());
        assertNotNull(dependencyInjector.getDashboardService());
        assertNotNull(dependencyInjector.getDeedService());
        assertNotNull(dependencyInjector.getTaxService());
    }

    @Test
    void testModelMapper() {
        assertNotNull(dependencyInjector.getModelMapper());
    }

    @Test
    void testScanner() {
        assertNotNull(dependencyInjector.getScanner());
    }

    @Test
    void testDice() {
        assertNotNull(dependencyInjector.getDice());
    }
}