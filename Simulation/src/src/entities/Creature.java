package src.entities;

import java.util.List;

import src.Map;
import src.Actions.TurnActions;

public abstract class Creature extends Entity {
	private int targetX, targetY;

	public Creature(int x, int y, int id, int hp) {
		super(x, y, id, hp);
	}

	public abstract void makeMove(Map map);

	protected void makeMove(Map map, List<? extends Entity> possibleTargets) {

		if (possibleTargets.size() == 0) { return; }
		Entity target = possibleTargets.get(0);
		targetX = target.getX();
		targetY = target.getY();

//		System.out.println("Targets: " + possibleTargets.get(0).toString());

		boolean canAttack = TurnActions.moveCreature(this, possibleTargets, map);

		if (canAttack) {
			this.damage(targetX, targetY, map);
//			System.out.print("Can attack!");
		}
	}

	public void damage(int x, int y, Map map) {
		Entity target = map.getEntityAt(x, y);
//		System.out.print("Got Entity at: ");
//		System.out.print(x);
//		System.out.print(y);
		if (target != null) {
			target.hp -= 10;
//			System.out.print(" HP = ");
//			System.out.print(target.hp);
		}
	}

}
