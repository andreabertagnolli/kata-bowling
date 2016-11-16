package ndr.brt;

public class BonusEarnedEvent extends Event {
    private final int bonus;

    public BonusEarnedEvent(int bonus) {
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }
}
