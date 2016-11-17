package ndr.brt;

import ndr.brt.event.BonusEarned;
import ndr.brt.event.Event;
import ndr.brt.event.GameCreated;
import ndr.brt.event.KnockedDown;

public class Publisher {

    private ScoreRepository scoreRepository = ScoreRepository.getInstance();

    public void publish(Event event) {
        if (event instanceof KnockedDown) {
            publish((KnockedDown) event);
        }
        if (event instanceof BonusEarned) {
            publish((BonusEarned) event);
        }
        if (event instanceof GameCreated) {
            publish((GameCreated) event);
        }
    }

    public void publish(KnockedDown event) {
        int actualScore = scoreRepository.getScore(event.getId());
        scoreRepository.setScore(actualScore + event.getPins(), event.getId());
    }

    public void publish(BonusEarned event) {
        int actualScore = scoreRepository.getScore(event.getId());
        scoreRepository.setScore(actualScore + event.getBonus(), event.getId());
    }

    public void publish(GameCreated event) {
        scoreRepository.setScore(0, event.getId());
    }
}
