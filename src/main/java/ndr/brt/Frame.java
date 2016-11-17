package ndr.brt;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class Frame {
    private List<Integer> rolls = new ArrayList<>();
    private boolean isLastFrame;

    public Frame(boolean isLastFrame) {
        this.isLastFrame = isLastFrame;
    }

    public boolean isComplete() {
        return !isLastFrame && (isStrike() || rolls.size() == 2);
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

    public boolean isStrike() {
        return score() == 10 && rolls.size() == 1;
    }

    public boolean isLast() {
        return isLastFrame;
    }

    public void addBonus(int count) {
        bonus += count;
    }

    private int score() {
        int score = 0;
        for (Integer roll : rolls) {
            score += roll;
        }
        return score;
    }

    @Override
    public String toString() {
        return rolls.stream()
                .map(String::valueOf)
                .collect(joining(",", "[", "]"));
    }
}
