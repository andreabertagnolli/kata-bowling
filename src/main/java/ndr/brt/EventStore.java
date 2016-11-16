package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class EventStore {

    private List<Event> events = new ArrayList<>();
    private Publisher publisher = new Publisher();

    public void store(List<Event> event) {
        events.addAll(event);
        event.forEach(e -> publisher.publish(e));
    }

    public List<Event> getAll() {
        return events;
    }
}
