package ndr.brt;

import java.util.HashMap;
import java.util.Map;

public class ScoreRepository {

    private static ScoreRepository instance;
    private Map<Integer, Integer> scores = new HashMap<>();

    public static ScoreRepository getInstance() {
        if (instance == null) {
            instance = new ScoreRepository();
        }
        return instance;
    }

    public int getScore(int id) {
        return scores.get(id);
    }

    public void setScore(int score, int id) {
        scores.put(id, score);
    }
}
