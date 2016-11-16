package ndr.brt;

public class CommandHandler {

    private ScoreRepository scoreRepository = ScoreRepository.getInstance();

    public void handle(RollCommand command) {
        GameEntity game = GameRepository.getInstance().get();

        game.roll(command.getPins());

        scoreRepository.setScore(game.score());
    }

}
