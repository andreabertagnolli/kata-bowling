package ndr.brt;

import ndr.brt.event.BonusEarned;
import ndr.brt.event.Event;
import ndr.brt.event.GameCreated;
import ndr.brt.event.KnockedDown;

import java.util.ArrayList;
import java.util.List;

public class GameEntity {

    private int id;
    private List<Frame> frames = new ArrayList<>();
    private List<Event> uncommittedEvents = new ArrayList<>();

    public void create(int id) {
        GameCreated event = new GameCreated(id);

        uncommittedEvents.add(event);
        apply(event);
    }

    public void roll(int pins, int id) {
        KnockedDown event = new KnockedDown(id, pins);

        uncommittedEvents.add(event);
        apply(event);
    }

    public void bonus(int pins, int id) {
        BonusEarned event = new BonusEarned(id, pins);

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
        if (event instanceof GameCreated) {
            apply((GameCreated) event);
        }
        if (event instanceof KnockedDown) {
            apply((KnockedDown) event);
        }
        if (event instanceof BonusEarned) {
            apply((BonusEarned) event);
        }
    }

    private void apply(GameCreated event) {
        setId(event.getId());
        frames.add(new Frame(false));
    }

    private void apply(KnockedDown event) {
        int pins = event.getPins();

        if (currentFrame().isComplete()) {
            Frame newFrame = new Frame(frames.size() == 9);
            frames.add(newFrame);
        }

        currentFrame().roll(pins);
    }

    private void apply(BonusEarned event) {
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
