package src;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore(int x, int y, int ID, int HP) {
        super(x, y, ID, 20); // HP: 50, Speed: 1
    }

    @Override
    public void makeMove(Map map) {
        	makeMove(map, map.getGrass());
        }

    @Override
    public String toString() {
    	return "Herbivore ID " + ID + ", " + X + ", " + Y;
    }

}