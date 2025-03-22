package src;

import java.util.List;

public class Predator extends Creature {

    public Predator(int x, int y, int id, int hp) {
        super(x, y, id, 20);
    }

    @Override
    public void makeMove(Map map) {
    	makeMove(map, map.getHerbivores());
    }

    @Override
    public String toString() {
    	return "Predator ID " + id + ", " + x + ", " + y;
    }

}
