package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Integer> rolls = new ArrayList<>();
    private int bonus = 0;

    public void roll(int count) {
        rolls.add(count);
        if (notTheFirstRoll() && firstRollOfAFrame() && precedentFrameWasASpare() && notABonusRoll()) {
            bonus += count;
        }
    }

    public int score() {
        int score = 0;
        for (Integer roll : rolls) {
            score += roll;
        }
        score += bonus;
        return score;
    }

    private boolean notABonusRoll() {
        return rolls.size() <= 20;
    }

    private boolean precedentFrameWasASpare() {
        return rolls.get(rolls.size() - 3) + rolls.get(rolls.size() - 2) == 10;
    }

    private boolean firstRollOfAFrame() {
        return rolls.size() % 2 == 1;
    }

    private boolean notTheFirstRoll() {
        return rolls.size() > 1;
    }
}
