package ndr.brt;

import java.util.ArrayList;
import java.util.List;

public class GameEntity {

    private int id;
    private List<Frame> frames = new ArrayList<>();
    private List<Event> uncommittedEvents = new ArrayList<>();

    public void create(int id) {
        GameCreatedEvent event = new GameCreatedEvent(id);

        uncommittedEvents.add(event);
        apply(event);
    }

    public void roll(int pins, int id) {
        KnockedDownEvent event = new KnockedDownEvent(pins, id);

        uncommittedEvents.add(event);
        apply(event);
    }

    public void bonus(int pins, int id) {
        BonusEarnedEvent event = new BonusEarnedEvent(pins, id);

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
        if (event instanceof GameCreatedEvent) {
            apply((GameCreatedEvent) event);
        }
        if (event instanceof KnockedDownEvent) {
            apply((KnockedDownEvent) event);
        }
        if (event instanceof BonusEarnedEvent) {
            apply((BonusEarnedEvent) event);
        }
    }

    private void apply(GameCreatedEvent event) {
        setId(event.getId());
        frames.add(new Frame(false));
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

    public void setId(int id) {
        this.id = id;
    }
}
