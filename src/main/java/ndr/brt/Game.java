package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Frame> frames = new ArrayList<>();
    private int currentFramePointer = 0;
    private int bonus = 0;

    public Game() {
        for (int i=0; i<9; i++) {
            this.frames.add(new Frame(false));
        }
        this.frames.add(new Frame(true));
    }

    public void roll(int count) {
        Frame currentFrame = getCurrentFrame();

        currentFrame.roll(count);

        if (precedentFrameWasASpare() && currentFrame.hasJustOneRoll()) {
            bonus += count;
        }
        if (precedentFrameWasAStrike() && !currentFrame.isLast()) {
            bonus += count;
        }
        if (precedentOfPrecedentFrameWasAStrike()) {
            bonus += count;
        }
    }

    private Frame getCurrentFrame() {
        Frame currentFrame = frames.get(currentFramePointer);
        if (currentFrame.isComplete()) {
            currentFramePointer ++;
            currentFrame = frames.get(currentFramePointer);
        }
        return currentFrame;
    }

    public int score() {
        int score = 0;
        for (Frame frame : frames) {
            score += frame.score();
        }
        score += bonus;
        return score;
    }

    private boolean precedentOfPrecedentFrameWasAStrike() {
        return currentFramePointer > 1 && frames.get(currentFramePointer - 2).isStrike();
    }

    private boolean precedentFrameWasAStrike() {
        return currentFramePointer > 0 && frames.get(currentFramePointer - 1).isStrike();
    }

    private boolean precedentFrameWasASpare() {
        return currentFramePointer > 0 && frames.get(currentFramePointer - 1).isSpare();
    }

}
