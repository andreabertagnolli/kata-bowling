package ndr.brt;

public class Publisher {

    private ScoreRepository scoreRepository = ScoreRepository.getInstance();

    public void publish(Event event) {
        if (event instanceof KnockedDownEvent) {
            publish((KnockedDownEvent) event);
        }
        if (event instanceof BonusEarnedEvent) {
            publish((BonusEarnedEvent) event);
        }
        if (event instanceof GameCreatedEvent) {
            publish((GameCreatedEvent) event);
        }
    }

    public void publish(KnockedDownEvent event) {
        int actualScore = scoreRepository.getScore(event.getId());
        scoreRepository.setScore(actualScore + event.getPins(), event.getId());
    }

    public void publish(BonusEarnedEvent event) {
        int actualScore = scoreRepository.getScore(event.getId());
        scoreRepository.setScore(actualScore + event.getBonus(), event.getId());
    }

    public void publish(GameCreatedEvent event) {
        scoreRepository.setScore(0, event.getId());
    }
}
