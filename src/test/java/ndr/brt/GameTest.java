package ndr.brt;

import ndr.brt.resource.GameCommandResource;
import ndr.brt.resource.GameQueryResource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameTest {

    private GameQueryResource gameQueryResource;
    private GameCommandResource gameCommandResource;

    @Before
    public void setUp() throws Exception {
        gameQueryResource = new GameQueryResource();
        gameCommandResource = new GameCommandResource();
        ScoreRepository.getInstance().clear();
    }

    @Test
    public void twenty_miss_rolls() throws Exception {
        rollMultiple(20, 0);

        assertEquals(0, gameQueryResource.score());
    }

    @Test
    public void twenty_rolls_that_scores_one() throws Exception {
        rollMultiple(20, 1);

        assertEquals(20, gameQueryResource.score());
    }

    @Test
    public void ten_spares_of_five_plus_five() throws Exception {
        rollMultiple(21, 5);

        assertEquals(150, gameQueryResource.score());
    }

    @Test
    public void ten_strikes() throws Exception {
        rollMultiple(12, 10);

        assertEquals(300, gameQueryResource.score());
    }

    private void rollMultiple(int times, int roll) {
        for (int i = 0; i < times; i++) {
            gameCommandResource.roll(roll);
        }
    }

}
