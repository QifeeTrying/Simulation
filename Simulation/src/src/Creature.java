package src;

import java.util.List;

public abstract class Creature extends Entity {
//    protected int hp;
//    protected int speed;

    public Creature(int x, int y, int ID, int HP) {
        super(x, y, ID, HP);
    }

	/*
	 * public int getHP() { return hp; }
	 */
    
    public abstract void makeMove(Map map);
    
    protected void makeMove(Map map, List<? extends Entity> possibleTargets) {
        
        boolean canAttack = TurnActions.moveCreature(this, possibleTargets, map);

        if (canAttack) {
            this.damage(targetCoords[0], targetCoords[1], map);
        }
    }

}
