package src;

public class Herbivore extends Creature {
    public Herbivore(int x, int y) {
        super(x, y, 50, 1); // HP: 50, Speed: 1
    }

    @Override
    public void makeMove(Map map) {

        int[] targetCoords = new int[2];        
        boolean canDestroy = TurnActions.moveCreature(this, map.getGrass(), map, targetCoords);
        if (canDestroy) {
            map.removeEntity(targetCoords[0], targetCoords[1]);
        }
    }

    
}