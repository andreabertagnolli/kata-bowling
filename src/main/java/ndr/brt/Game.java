package ndr.brt;

public class Game {

    private int score;

    public void roll(int count) {
        score += count;
    }

    public int score() {
        return score;
    }
}
