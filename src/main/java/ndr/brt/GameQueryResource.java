package ndr.brt;

public class GameQueryResource {

    private ScoreRepository scoreRepository = ScoreRepository.getInstance();

    public int score() {
        return scoreRepository.getScore();
    }

}
