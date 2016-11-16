package ndr.brt;

import java.util.List;

public class GameRepository {
    private static GameRepository instance;

    public static GameRepository getInstance() {
        if (instance == null) {
            instance = new GameRepository();
        }
        return instance;
    }

    public GameEntity get() {
        List<Event> events = EventStore.getInstance().getAll();
        GameEntity game = new GameEntity();
        for (Event event : events) {
            game.apply(event);
        }
        return game;
    }

    public void clear() {
        instance = null;
    }

    public void save(GameEntity game) {
        EventStore.getInstance().store(game.uncommittedEvents());
    }
}
