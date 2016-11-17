package ndr.brt;

public class KnockedDownEvent extends Event {
    private int pins;

    public KnockedDownEvent(int pins, int id) {
        super(id);
        this.pins = pins;
    }

    public int getPins() {
        return pins;
    }
}
