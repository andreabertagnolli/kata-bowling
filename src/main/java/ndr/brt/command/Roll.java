package ndr.brt.command;

public class Roll {
    private int pins;
    private final int id;

    public Roll(int pins, int id) {
        this.pins = pins;
        this.id = id;
    }

    public int getPins() {
        return pins;
    }

    public int getId() {
        return id;
    }
}
