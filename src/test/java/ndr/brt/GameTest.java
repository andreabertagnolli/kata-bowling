package ndr.brt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private Game game;

    @Before
    public void setUp() throws Exception {
        game = new Game();
    }

    @Test
    public void twenty_miss_rolls() throws Exception {
        rollMultiple(20, 0);

        assertEquals(0, game.score());
    }

    @Test
    public void twenty_rolls_that_scores_one() throws Exception {
        rollMultiple(20, 1);

        assertEquals(20, game.score());
    }

    private void rollMultiple(int times, int roll) {
        for (int i = 0; i < times; i++) {
            game.roll(roll);
        }
    }

}
