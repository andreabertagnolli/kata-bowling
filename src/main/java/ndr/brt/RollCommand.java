package ndr.brt;

public class RollCommand {
    private int pins;
    private final int id;

    public RollCommand(int pins, int id) {
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
