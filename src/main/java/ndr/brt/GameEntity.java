package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class GameEntity {

    private List<Frame> frames = new ArrayList<>();
    private Publisher publisher = new Publisher();

    public GameEntity() {
        frames.add(new Frame(false));
    }

    public void roll(int pins) {
        KnockedDownEvent event = new KnockedDownEvent(pins);

        EventStore.getInstance().store(event);
        apply(event);
        publisher.publish(event);
    }

    public void bonus(int pins) {
        BonusEarnedEvent event = new BonusEarnedEvent(pins);

        EventStore.getInstance().store(event);
        apply(event);
        publisher.publish(event);
    }

    public void apply(Event event) {
        if (event instanceof KnockedDownEvent) {
            KnockedDownEvent knockedDown = (KnockedDownEvent)event;
            int pins = knockedDown.getPins();

            if (currentFrame().isComplete()) {
                Frame newFrame = new Frame(frames.size() == 9);
                frames.add(newFrame);
            }

            currentFrame().roll(pins);

            if (shouldGiveSpareBonus()) {
                precedentFrame().addBonus(pins);
            }
            if (shouldGiveStrikeBonus()) {
                precedentFrame().addBonus(pins);
            }
            if (shouldGiveStrikeSecondBonus()) {
                precedentOfPrecedentFrame().addBonus(pins);
            }
        }
    }

    public boolean shouldGiveSpareBonus() {
        return precedentFrameWasASpare() && currentFrame().hasJustOneRoll();
    }

    boolean shouldGiveStrikeBonus() {
        return precedentFrameWasAStrike() && !currentFrame().isLast();
    }

    boolean shouldGiveStrikeSecondBonus() {
        return currentFramePointer() > 1 && precedentOfPrecedentFrame().isStrike();
    }

    private boolean precedentFrameWasAStrike() {
        return currentFramePointer() > 0 && precedentFrame().isStrike();
    }

    boolean precedentFrameWasASpare() {
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
