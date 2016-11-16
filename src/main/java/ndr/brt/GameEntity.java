package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class GameEntity {

    private List<Frame> frames = new ArrayList<>();
    private List<Event> uncommittedEvents = new ArrayList<>();

    public GameEntity() {
        frames.add(new Frame(false));
    }

    public void roll(int pins) {
        KnockedDownEvent event = new KnockedDownEvent(pins);

        uncommittedEvents.add(event);
        apply(event);
    }

    public void bonus(int pins) {
        BonusEarnedEvent event = new BonusEarnedEvent(pins);

        uncommittedEvents.add(event);
        apply(event);
    }

    public List<Event> uncommittedEvents() {
        return uncommittedEvents;
    }

    public boolean shouldGiveSpareBonus() {
        return precedentFrameWasASpare() && currentFrame().hasJustOneRoll();
    }

    public boolean shouldGiveStrikeBonus() {
        return precedentFrameWasAStrike() && !currentFrame().isLast();
    }

    public boolean shouldGiveStrikeSecondBonus() {
        return currentFramePointer() > 1 && precedentOfPrecedentFrame().isStrike();
    }

    public void apply(Event event) {
        if (event instanceof KnockedDownEvent) {
            apply((KnockedDownEvent) event);
        }
        if (event instanceof BonusEarnedEvent) {
            apply((BonusEarnedEvent) event);
        }
    }

    private void apply(KnockedDownEvent event) {
        int pins = event.getPins();

        if (currentFrame().isComplete()) {
            Frame newFrame = new Frame(frames.size() == 9);
            frames.add(newFrame);
        }

        currentFrame().roll(pins);
    }

    private void apply(BonusEarnedEvent event) {
        int bonus = event.getBonus();
        if (shouldGiveSpareBonus()) {
            precedentFrame().addBonus(bonus);
        }
        if (shouldGiveStrikeBonus()) {
            precedentFrame().addBonus(bonus);
        }
        if (shouldGiveStrikeSecondBonus()) {
            precedentOfPrecedentFrame().addBonus(bonus);
        }
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
