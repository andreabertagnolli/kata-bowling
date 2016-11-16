package ndr.brt;

public class GameCommandResource {

    private CommandHandler commandHandler;

    public GameCommandResource() {
        this.commandHandler = new CommandHandler();
    }

    public void roll(int count) {
        RollCommand command = new RollCommand(count);

        commandHandler.handle(command);
    }
}
