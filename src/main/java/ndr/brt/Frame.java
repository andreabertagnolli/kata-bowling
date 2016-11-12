package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class Frame {
    private List<Integer> rolls = new ArrayList<>();
    private boolean isLastFrame;

    public Frame(boolean isLastFrame) {
        this.isLastFrame = isLastFrame;
    }

    public boolean isComplete() {
        return rolls.size() == 2 && !isLastFrame;
    }

    public void roll(int count) {
        rolls.add(count);
    }

    public boolean hasJustOneRoll() {
        return rolls.size() == 1;
    }

    public boolean isSpare() {
        return score() == 10 && rolls.size() == 2;
    }

    public int score() {
        int score = 0;
        for (int i = 0; i < rolls.size(); i++) {
            score += rolls.get(i);
        }
        return score;
    }
}
