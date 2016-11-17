package ndr.brt.resource;

import ndr.brt.CommandHandler;
import ndr.brt.command.CreateNewGame;
import ndr.brt.command.Roll;

public class GameCommandResource {

    private CommandHandler commandHandler;

    public GameCommandResource() {
        this.commandHandler = new CommandHandler();
    }

    public int newGame(int id) {
        CreateNewGame command = new CreateNewGame(id);

        commandHandler.handle(command);

        return id;
    }

    public void roll(int count, int gameId) {
        Roll command = new Roll(count, gameId);

        commandHandler.handle(command);
    }
}
