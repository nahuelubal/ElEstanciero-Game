package ar.edu.utn.frc.tup.lciii.others.console_writer;

import lombok.Getter;

@Getter
public class ConsoleConfig {
    private final Align align;
    private final boolean bold;
    private final int size;
    private final Integer DEFAULT_SIZE = 181;

    public ConsoleConfig() {
        this.align = Align.LEFT;
        this.bold = false;
        this.size = DEFAULT_SIZE;
    }

    public ConsoleConfig(Align align) {
        this.align = align;
        this.bold = false;
        this.size = DEFAULT_SIZE;
    }

    public ConsoleConfig(Align align, Boolean bold) {
        this.align = align;
        this.bold = bold;
        this.size = DEFAULT_SIZE;
    }

    public ConsoleConfig(Align align, int size) {
        this.align = align;
        this.bold = false;
        this.size = size;
    }

    public ConsoleConfig(Align align, boolean bold, int size) {
        this.align = align;
        this.bold = bold;
        this.size = size;
    }
}