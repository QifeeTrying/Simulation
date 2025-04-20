package src.entities;

import java.util.List;

import src.Map;

public class Herbivore extends Creature {
    public Herbivore(int x, int y, int id, int hp) {
        super(x, y, id, 20);
    }

    @Override
    public void makeMove(Map map) {
        	makeMove(map, map.getGrass());
        }

    @Override
    public String toString() {
    	return "Herbivore ID " + id + ", " + x + ", " + y;
    }

}