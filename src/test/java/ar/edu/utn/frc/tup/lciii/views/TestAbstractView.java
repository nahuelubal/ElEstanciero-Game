package ar.edu.utn.frc.tup.lciii.views;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.MockitoAnnotations.openMocks;

class TestAbstractView {
    private AbstractView abstractView;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        abstractView = new ConcreteView();
    }

    @Test
    public void testConstructor() {
        assertNotNull(abstractView, "AbstractView should be instantiated");
    }

    @Test
    public void testRender() {
        abstractView.render();
    }
}