package ndr.brt.resource;

import ndr.brt.CommandHandler;
import ndr.brt.RollCommand;

public class GameCommandResource {

    private CommandHandler commandHandler;

    public GameCommandResource() {
        this.commandHandler = new CommandHandler();
    }

    public int newGame(int id) {
        CreateNewGameCommand command = new CreateNewGameCommand(id);

        commandHandler.handle(command);

        return id;
    }

    public void roll(int count) {
        RollCommand command = new RollCommand(count);

        commandHandler.handle(command);
    }
}
