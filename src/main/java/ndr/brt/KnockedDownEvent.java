package ndr.brt;

public class KnockedDownEvent extends Event {
    private int pins;

    public KnockedDownEvent(int pins) {
        this.pins = pins;
    }

    public int getPins() {
        return pins;
    }
}
