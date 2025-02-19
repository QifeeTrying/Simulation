package src;

public class Herbivore extends Creature {
    public Herbivore(int x, int y) {
        super(x, y, 50, 1); // HP: 50, Speed: 1
    }

    @Override
    public void makeMove(Map map) {

        System.out.println("HerbiMove");
        int[] targetCoords = new int[2]; // Array to store target's coordinates
        
        boolean canDestroy = TurnActions.moveCreature(this, map.getGrass(), map, targetCoords); // Move and get coords

        if (canDestroy) { // If adjacent to Grass
            map.removeEntity(targetCoords[0], targetCoords[1]); // Remove Grass at the stored coordinates
        }
    }

    
}