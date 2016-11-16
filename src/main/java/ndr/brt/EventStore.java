package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class EventStore {

    private static EventStore instance;
    private List<Event> events = new ArrayList<>();
    private Publisher publisher = new Publisher();

    public static EventStore getInstance() {
        if (instance == null) {
            instance = new EventStore();
        }
        return instance;
    }

    public void store(List<Event> event) {
        events.addAll(event);
        event.forEach(e -> publisher.publish(e));
    }

    public List<Event> getAll() {
        return events;
    }

    public void clear() {
        events.clear();
    }
}
