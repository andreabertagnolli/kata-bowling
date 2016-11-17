package ndr.brt.event;

public class KnockedDown extends Event {

    private int pins;

    public KnockedDown(int id, int pins) {
        super(id);
        this.pins = pins;
    }

    public int getPins() {
        return pins;
    }
}
