package src;

public class Predator extends Creature {
    private int attackPower;

    public Predator(int x, int y) {
        super(x, y, 80, 1); // HP: 80, Speed: 1
        this.attackPower = 25;
    }

    @Override
    public void makeMove(Map map) {
    }

    public void attack(Herbivore target) {
        target.takeDamage(attackPower);
    }
}
