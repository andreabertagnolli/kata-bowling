package ndr.brt;

public class CommandHandler {

    public void handle(RollCommand command) {
        GameEntity game = GameRepository.getInstance().get();

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
    }

}
