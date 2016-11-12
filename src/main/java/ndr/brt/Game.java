package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Integer> rolls = new ArrayList<>();

    public void roll(int count) {
        rolls.add(count);
    }

    public int score() {
        int score = 0;
        for (Integer roll : rolls) {
            score += roll;
        }
        return score;
    }
}
