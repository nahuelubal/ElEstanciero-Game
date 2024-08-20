package ar.edu.utn.frc.tup.lciii.views;

import ar.edu.utn.frc.tup.lciii.others.dependency_injection.AbstractDependencyInjector;

public abstract class AbstractView extends AbstractDependencyInjector {
    public AbstractView() {
        super();
    }

    public abstract void render();
}
