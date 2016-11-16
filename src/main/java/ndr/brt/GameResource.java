package ndr.brt;

public class GameResource {

    private CommandHandler commandHandler = new CommandHandler();
    private ScoreRepository scoreRepository = ScoreRepository.getInstance();

    public void roll(int count) {
        RollCommand command = new RollCommand(count);

        commandHandler.handle(command);
    }

    public int score() {
        return scoreRepository.getScore();
    }

}
