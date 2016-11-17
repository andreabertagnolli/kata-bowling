package ndr.brt;

import ndr.brt.resource.CreateNewGameCommand;

public class CommandHandler {

    private GameRepository repository;

    public CommandHandler() {
        repository = new GameRepository();
    }

    public void handle(CreateNewGameCommand command) {
        GameEntity game = new GameEntity();

        game.create(command.getId());

        repository.save(game);
    }

    public void handle(RollCommand command) {
        GameEntity game = repository.get();

        int pins = command.getPins();
        game.roll(pins);

        if (game.shouldGiveSpareBonus()) {
            game.bonus(pins);
        }
        if (game.shouldGiveStrikeBonus()) {
            game.bonus(pins);
        }
        if (game.shouldGiveStrikeSecondBonus()) {
            game.bonus(pins);
        }

        repository.save(game);
    }
}
