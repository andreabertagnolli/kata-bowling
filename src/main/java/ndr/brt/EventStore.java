package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class EventStore {

    private static EventStore instance;
    private List<KnockedDownEvent> events = new ArrayList<>();

    public static EventStore getInstance() {
        if (instance == null) {
            instance = new EventStore();
        }
        return instance;
    }

    public void store(KnockedDownEvent knockedDownEvent) {
        events.add(knockedDownEvent);
    }

    public List<KnockedDownEvent> getAll() {
        return events;
    }

    public void clear() {
        events.clear();
    }
}
