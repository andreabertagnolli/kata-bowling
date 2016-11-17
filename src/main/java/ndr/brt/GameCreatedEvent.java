package ndr.brt;

public class GameCreatedEvent extends Event {
    private final int id;

    public GameCreatedEvent(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
