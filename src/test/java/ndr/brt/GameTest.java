package ndr.brt;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    @Test
    public void twenty_miss_rolls() throws Exception {
        Game game = new Game();

        for (int i = 0; i < 20; i++) {
            game.roll(0);
        }

        assertEquals(0, game.score());
    }

    @Test
    public void twenty_rolls_that_scores_one() throws Exception {
        Game game = new Game();

        for (int i = 0; i < 20; i++) {
            game.roll(1);
        }

        assertEquals(20, game.score());
    }
}
