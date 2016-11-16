package ndr.brt;

public class Publisher {

    private ScoreRepository scoreRepository = ScoreRepository.getInstance();

    public void publish(KnockedDownEvent event) {
        int actualScore = scoreRepository.getScore();
        scoreRepository.setScore(actualScore + event.getPins());
    }

    public void publish(BonusEarnedEvent event) {
        int actualScore = scoreRepository.getScore();
        scoreRepository.setScore(actualScore + event.getBonus());
    }
}
