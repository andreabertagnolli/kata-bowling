package ndr.brt.resource;

public class CreateNewGameCommand {
    private final int id;

    public CreateNewGameCommand(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
