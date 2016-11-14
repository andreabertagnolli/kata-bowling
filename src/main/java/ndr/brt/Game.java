package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Frame> frames = new ArrayList<>();
    private int currentFramePointer = 0;

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
            precedentFrame().addBonus(count);
        }
        if (precedentFrameWasAStrike() && !currentFrame.isLast()) {
            precedentFrame().addBonus(count);
        }
        if (precedentOfPrecedentFrameWasAStrike()) {
            precedentOfPrecedentFrame().addBonus(count);
        }
    }

    public int score() {
        int score = 0;
        for (Frame frame : frames) {
            score += frame.score();
        }
        return score;
    }

    private Frame getCurrentFrame() {
        Frame currentFrame = frames.get(currentFramePointer);
        if (currentFrame.isComplete()) {
            currentFramePointer ++;
            currentFrame = frames.get(currentFramePointer);
        }
        return currentFrame;
    }

    private boolean precedentOfPrecedentFrameWasAStrike() {
        return currentFramePointer > 1 && precedentOfPrecedentFrame().isStrike();
    }

    private boolean precedentFrameWasAStrike() {
        return currentFramePointer > 0 && precedentFrame().isStrike();
    }

    private boolean precedentFrameWasASpare() {
        return currentFramePointer > 0 && precedentFrame().isSpare();
    }

    private Frame precedentOfPrecedentFrame() {
        return frames.get(currentFramePointer - 2);
    }

    private Frame precedentFrame() {
        return frames.get(currentFramePointer - 1);
    }

}
