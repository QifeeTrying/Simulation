package src;

public class Predator extends Creature {
    private int attackPower;

    public Predator(int x, int y) {
        super(x, y, 80, 1); // HP: 80, Speed: 1
        this.attackPower = 25;
    }

    @Override
    public void makeMove(Map map) {

        int[] targetCoords = new int[2]; // Array to store target's coordinates
        
        boolean canDestroy = TurnActions.moveCreature(this, map.getHerbivores(), map, targetCoords); // Move and get coords

        if (canDestroy) { // If adjacent to Grass
            map.removeEntity(targetCoords[0], targetCoords[1]); // Remove Grass at the stored coordinates
        }
    }

}
