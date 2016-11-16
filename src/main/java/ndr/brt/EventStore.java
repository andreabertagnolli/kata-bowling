package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class EventStore {

    private static EventStore instance;
    private List<Event> events = new ArrayList<>();

    public static EventStore getInstance() {
        if (instance == null) {
            instance = new EventStore();
        }
        return instance;
    }

    public void store(Event event) {
        events.add(event);
    }

    public List<Event> getAll() {
        return events;
    }

    public void clear() {
        events.clear();
    }
}
