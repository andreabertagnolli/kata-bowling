package ndr.brt;

public class GameRepository {

    private EventStore eventStore;

    public GameRepository() {
        eventStore = new EventStore();
    }

    public GameEntity get(int id) {
        GameEntity game = new GameEntity();
        eventStore.getById(id).forEach(game::apply);
        return game;
    }

    public void save(GameEntity game) {
        eventStore.store(game.uncommittedEvents());
    }
}
