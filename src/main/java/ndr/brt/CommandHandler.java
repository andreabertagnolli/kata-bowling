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
        int id = command.getId();
        GameEntity game = repository.get(id);

        int pins = command.getPins();
        game.roll(pins, id);

        if (game.shouldGiveSpareBonus()) {
            game.bonus(pins, id);
        }
        if (game.shouldGiveStrikeBonus()) {
            game.bonus(pins, id);
        }
        if (game.shouldGiveStrikeSecondBonus()) {
            game.bonus(pins, id);
        }

        repository.save(game);
    }
}
