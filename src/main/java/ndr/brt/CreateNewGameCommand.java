package ndr.brt;

public class CreateNewGameCommand {
    private final int id;

    public CreateNewGameCommand(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
