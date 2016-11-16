package ndr.brt;

public class GameRepository {

    private EventStore eventStore;

    public GameRepository() {
        eventStore = new EventStore();
    }

    public GameEntity get() {
        GameEntity game = new GameEntity();
        eventStore.getAll().forEach(game::apply);
        return game;
    }

    public void save(GameEntity game) {
        eventStore.store(game.uncommittedEvents());
    }
}
