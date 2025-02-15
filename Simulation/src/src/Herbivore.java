package src;

public class Herbivore extends Creature {
    public Herbivore(int x, int y) {
        super(x, y, 50, 1); // HP: 50, Speed: 1
    }

    @Override
    public void makeMove(Map map) {
        // Тут буде логіка пошуку їжі та переміщення
    }

}
