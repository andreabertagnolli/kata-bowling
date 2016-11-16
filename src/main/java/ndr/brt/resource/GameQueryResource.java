package ndr.brt.resource;

import ndr.brt.ScoreRepository;

public class GameQueryResource {

    private ScoreRepository scoreRepository = ScoreRepository.getInstance();

    public int score() {
        return scoreRepository.getScore();
    }

}
