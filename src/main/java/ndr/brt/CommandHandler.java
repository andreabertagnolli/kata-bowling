package ndr.brt;

import ndr.brt.command.NewGame;
import ndr.brt.command.Roll;

public class CommandHandler {

    private GameRepository repository;

    public CommandHandler() {
        repository = new GameRepository();
    }

    public void handle(NewGame command) {
        GameEntity game = new GameEntity();

        game.create(command.getId());

        repository.save(game);
    }

    public void handle(Roll command) {
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
