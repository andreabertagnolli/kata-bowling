package ndr.brt.event;

public class Event {

    private final int id;

    protected Event(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
