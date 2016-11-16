package ndr.brt;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private GameResource gameResource;

    @Before
    public void setUp() throws Exception {
        gameResource = new GameResource();
        EventStore.getInstance().clear();
        ScoreRepository.getInstance().clear();
        GameRepository.getInstance().clear();
    }

    @Test
    public void twenty_miss_rolls() throws Exception {
        rollMultiple(20, 0);

        assertEquals(0, gameResource.score());
    }

    @Test
    public void twenty_rolls_that_scores_one() throws Exception {
        rollMultiple(20, 1);

        assertEquals(20, gameResource.score());
    }

    @Test
    public void ten_spares_of_five_plus_five() throws Exception {
        rollMultiple(21, 5);

        assertEquals(150, gameResource.score());
    }

    @Test
    public void ten_strikes() throws Exception {
        rollMultiple(12, 10);

        assertEquals(300, gameResource.score());
    }

    private void rollMultiple(int times, int roll) {
        for (int i = 0; i < times; i++) {
            gameResource.roll(roll);
        }
    }

}
