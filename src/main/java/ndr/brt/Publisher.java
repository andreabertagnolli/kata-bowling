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
    }

    public void publish(KnockedDownEvent event) {
        int actualScore = scoreRepository.getScore();
        scoreRepository.setScore(actualScore + event.getPins());
    }

    public void publish(BonusEarnedEvent event) {
        int actualScore = scoreRepository.getScore();
        scoreRepository.setScore(actualScore + event.getBonus());
    }
}
