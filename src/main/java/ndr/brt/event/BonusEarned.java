package ndr.brt.event;

public class BonusEarned extends Event {
    private final int bonus;

    public BonusEarned(int id, int bonus) {
        super(id);
        this.bonus = bonus;
    }

    public int getBonus() {
        return bonus;
    }
}
