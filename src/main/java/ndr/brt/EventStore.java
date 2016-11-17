package ndr.brt;

import ndr.brt.event.Event;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class EventStore {

    private List<Event> events = new ArrayList<>();
    private Publisher publisher = new Publisher();

    public void store(List<Event> event) {
        events.addAll(event);
        event.forEach(e -> publisher.publish(e));
    }

    public List<Event> getById(int id) {
        return events.stream()
                .filter(e -> id == e.getId())
                .collect(toList());
    }
}
