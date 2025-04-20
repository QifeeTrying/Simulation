package src.entities;

import java.util.List;

import src.Map;
import src.Actions.TurnActions;

public abstract class Creature extends Entity {
    private int targetX, targetY; // Store target coordinates

    public Creature(int x, int y, int id, int hp) {
        super(x, y, id, hp);
    }
    
    public abstract void makeMove(Map map);
    
    protected void makeMove(Map map, List<? extends Entity> possibleTargets) {
        
        Entity target = possibleTargets.get(0);
        targetX = target.getX();
        targetY = target.getY();
        
        boolean canAttack = TurnActions.moveCreature(this, possibleTargets, map);

        if (canAttack) {
            this.damage(targetX, targetY, map);
        }
    }
    
    public void damage(int x, int y, Map map) {
        Entity target = map.getEntityAt(x, y);
        if (target != null) {
        target.hp -= 10;
        }
    }

}
