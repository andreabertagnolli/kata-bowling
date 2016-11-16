package ndr.brt;

public class GameRepository {
    private static GameRepository instance;
    private EventStore eventStore;

    public static GameRepository getInstance() {
        if (instance == null) {
            instance = new GameRepository();
        }
        return instance;
    }

    private GameRepository() {
        eventStore = new EventStore();
    }

    public GameEntity get() {
        GameEntity game = new GameEntity();
        eventStore.getAll().forEach(game::apply);
        return game;
    }

    public void clear() {
        instance = null;
    }

    public void save(GameEntity game) {
        eventStore.store(game.uncommittedEvents());
    }
}
