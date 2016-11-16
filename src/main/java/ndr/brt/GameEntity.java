package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class GameEntity {

    private List<Frame> frames = new ArrayList<>();

    public GameEntity() {
        frames.add(new Frame(false));
    }

    public void roll(int pins) {
        KnockedDownEvent event = new KnockedDownEvent(pins);

        EventStore.getInstance().store(event);

        apply(event);
    }

    public void apply(KnockedDownEvent event) {
        int pins = event.getPins();

        if (currentFrame().isComplete()) {
            Frame newFrame = new Frame(frames.size() == 9);
            frames.add(newFrame);
        }

        currentFrame().roll(pins);

        if (precedentFrameWasASpare() && currentFrame().hasJustOneRoll()) {
            precedentFrame().addBonus(pins);
        }
        if (precedentFrameWasAStrike() && !currentFrame().isLast()) {
            precedentFrame().addBonus(pins);
        }
        if (precedentOfPrecedentFrameWasAStrike()) {
            precedentOfPrecedentFrame().addBonus(pins);
        }
    }

    public int score() {
        int score = 0;
        for (Frame frame : frames) {
            score += frame.score();
        }
        return score;
    }

    private boolean precedentOfPrecedentFrameWasAStrike() {
        return currentFramePointer() > 1 && precedentOfPrecedentFrame().isStrike();
    }

    private boolean precedentFrameWasAStrike() {
        return currentFramePointer() > 0 && precedentFrame().isStrike();
    }

    private boolean precedentFrameWasASpare() {
        return currentFramePointer() > 0 && precedentFrame().isSpare();
    }

    private Frame currentFrame() {
        return frames.get(currentFramePointer());
    }

    private Frame precedentOfPrecedentFrame() {
        return frames.get(currentFramePointer() - 2);
    }

    private Frame precedentFrame() {
        return frames.get(currentFramePointer() - 1);
    }

    private int currentFramePointer() {
        return frames.size()-1;
    }
}
