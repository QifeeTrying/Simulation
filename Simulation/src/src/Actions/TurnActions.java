package src.Actions;

import java.util.*;

import src.Map;
import src.entities.Creature;
import src.entities.Entity;

public class TurnActions {
    private static HashMap<Integer, int[]> entityPositions = new HashMap<>();

	public static List<int[]> findPath(Entity source, Entity target, Map map) {
        int startX = source.getX();
        int startY = source.getY();
        int targetX = target.getX();
        int targetY = target.getY();

        HashMap<String, int[]> cameFrom = new HashMap<>();
        cameFrom.put(startX + "," + startY, new int[]{startX, startY});
        Set<String> visited = new HashSet<>();

        visited.add(startX + "," + startY);
        
        int curX = startX;
        int curY = startY;
        int direction = 0;
        int lowest = 0;
        ArrayList<Integer> priorities = new ArrayList<>(Collections.nCopies(8, 0));

        if (startX > targetX) {
        	priorities.set(0, priorities.get(0) + 1);
        	priorities.set(3, priorities.get(3) + 1);
        	priorities.set(5, priorities.get(5) + 1);
        }
        if (startX < targetX) {
        	priorities.set(2, priorities.get(2) + 1);
        	priorities.set(4, priorities.get(4) + 1);
        	priorities.set(7, priorities.get(7) + 1);
        }
        else
        {
        	if (startY < targetY) {
        	priorities.set(1, priorities.get(1) + 1);
        	}
        	if (startY > targetY) {
        	priorities.set(6, priorities.get(6) + 1);
        	}
        }
        
        if (startY > targetY) {
        	priorities.set(5, priorities.get(5) + 1);
        	priorities.set(6, priorities.get(6) + 1);
        	priorities.set(7, priorities.get(7) + 1);
        }
        if (startY < targetY) {
        	priorities.set(0, priorities.get(0) + 1);
        	priorities.set(1, priorities.get(1) + 1);
        	priorities.set(2, priorities.get(2) + 1);
        }
        else
        {
        	if (startX > targetX) {
        	priorities.set(3, priorities.get(3) + 1);
        	}
        	if (startX < targetX) {
        	priorities.set(4, priorities.get(4) + 1);
        	}
        }
        
        if (!isWalkable(startX - 1, startY + 1, map)) {
            	priorities.set(0, 0);
        	}
        if (!isWalkable(startX, startY + 1, map)) {
            	priorities.set(1, 0);
       		}
        if (!isWalkable(startX + 1, startY + 1, map)) {
            	priorities.set(2, 0);
       		}
        if (!isWalkable(startX - 1, startY, map)) {
            	priorities.set(3, 0);
       		}
        if (!isWalkable(startX - 1, startY - 1, map)) {
            	priorities.set(4, 0);
       		}
        if (!isWalkable(startX + 1, startY, map)) {
            	priorities.set(5, 0);
       		}
        if (!isWalkable(startX + 1, startY - 1, map)) {
            	priorities.set(6, 0);
       		}
        if (!isWalkable(startX, startY - 1, map)) {
            	priorities.set(7, 0);
        }
        
        for (int i = 0; i < 8; i++) {
            if (priorities.get(i) > lowest) {
                direction = i;
                lowest = priorities.get(i);
            }
        }
        
        switch (direction) {
        case 0: curX--; curY++; System.out.println("0"); break; // top-left
        case 1: curY++; System.out.println("1"); break;          // top
        case 2: curX++; curY++; System.out.println("2"); break; // top-right
        case 3: curX--; System.out.println("3"); break;          // left
        case 4: curX++; System.out.println("4"); break;          // right
        case 5: curX--; curY--; System.out.println("5"); break; // bottom-left
        case 6: curY--; System.out.println("6"); break;          // bottom
        case 7: curX++; curY--; System.out.println("7"); break; // bottom-right
        default: System.out.println("default"); break; //nowhere
    }
        
		List<int[]> path = new ArrayList<>();
		path.add(new int[] {curX, curY});
        return path;
        
    }


	private static boolean isWalkable(int x, int y, Map map) {
        if (!map.isValid(x, y)) {
            return false;
        }

        for (int[] pos : entityPositions.values()) {
            if (pos[0] == x && pos[1] == y) {
                return false;
            }
        }

        return true;
    }
	
	public static boolean moveCreature(Creature creature, List<? extends Entity> possibleTargets, Map map) {
		Entity nearestTarget = findNearestTarget(creature, possibleTargets);
//		System.out.println("TargetCoords: " + targetCoords[0] + ", " + targetCoords[1]);

		if (nearestTarget == null) {
			System.out.println("No target found");			
			return false;
		}

		List<int[]> path = findPath(creature, nearestTarget, map);
		if (path != null && path.size() > 0) {
			int[] nextStep = path.get(0);

			creature.setPosition(nextStep[0], nextStep[1]);

// змінить на мап ліст ентіті			entityPositions.put(creature.getID(), new int[] { nextStep[0], nextStep[1] });

		}
	    boolean canAttackEntity = canAttackEntity(creature, nearestTarget, map);
	    return canAttackEntity;
	}
	

	public static boolean canAttackEntity(Entity source, Entity target, Map map) {
		int startX = source.getX();
		int startY = source.getY();
		int targetX = target.getX();
		int targetY = target.getY();
		if (targetX == startX++ && targetY == startY){
			return true;
		}
		if (targetX == startX && targetY == startY++){
			return true;
		}
		if (targetX == startX-- && targetY == startY){
			return true;
		}
		if (targetX == startX && targetY == startY--){
			return true;
		}
		return false;
	}

	public static Entity findNearestTarget(Creature creature, List<? extends Entity> targets) {
		int startX = creature.getX();
		int startY = creature.getY();
		Entity nearest = null;
		int minDistance = Integer.MAX_VALUE;

		for (Entity target : targets) {
			int targetX = target.getX();
			int targetY = target.getY();
			int distance = Math.abs(targetX - startX) + Math.abs(targetY - startY);

			if (distance < minDistance) {
				minDistance = distance;
				nearest = target;
			}
		}
		return nearest;
	}
	
}
