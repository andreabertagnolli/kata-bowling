package ndr.brt;

public class ScoreRepository {

    private static ScoreRepository instance;
    private int score = 0;

    public static ScoreRepository getInstance() {
        if (instance == null) {
            instance = new ScoreRepository();
        }
        return instance;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void clear() {
        score = 0;
    }
}
