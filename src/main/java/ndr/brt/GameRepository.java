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
        List<KnockedDownEvent> events = EventStore.getInstance().getAll();
        GameEntity game = new GameEntity();
        for (KnockedDownEvent event : events) {
            game.apply(event);
        }
        return game;
    }

    public void clear() {
        instance = null;
    }
}
