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
        int[] priorities = new int[8]; 

        if (startX > targetX) {
        	priorities[0] += 1;
        	priorities[3] += 1;
        	priorities[5] += 1;
        }
        if (startX < targetX) {
        	priorities[2] += 1;
        	priorities[4] += 1;
        	priorities[7] += 1;
        }
        else
        {
        	if (startY < targetY) {
        	priorities[1] += 1;
        	}
        	if (startY > targetY) {
        	priorities[6] += 1;
        	}
        }
        
        if (startY > targetY) {
        	priorities[5] += 1;
        	priorities[6] += 1;
        	priorities[7] += 1;
        }
        if (startY < targetY) {
        	priorities[0] += 1;
        	priorities[1] += 1;
        	priorities[2] += 1;
        }
        else
        {
        	if (startX > targetX) {
        	priorities[3] += 1;
        	}
        	if (startX < targetX) {
        	priorities[4] += 1;
        	}
        }
        
  
        for (int i = 0; i < 8; i++) {
            if (priorities[i] > direction) {
                direction = priorities[i] + 1;
            }
            if (i == 0) {
            	if (isWalkable(startX - 1, startY + 1, map) == false) {
            		direction = 0;
            	}
            }
           	if (i == 1) {
           		if (isWalkable(startX, startY + 1, map) == false) {
           			direction = 0;
           		}
           	}
           	if (i == 2) {
           		if (isWalkable(startX + 1, startY + 1, map) == false) {
           			direction = 0;
           		}
           	}
           	if (i == 3) {
           		if (isWalkable(startX - 1, startY, map) == false) {
           			direction = 0;
           		}
           	}
           	if (i == 4) {
           		if (isWalkable(startX - 1, startY - 1, map) == false) {
           			direction = 0;
           		}
           	}
           	if (i == 5) {
           		if (isWalkable(startX + 1, startY, map) == false) {
           			direction = 0;
           		}
           	}
           	if (i == 6) {
           		if (isWalkable(startX + 1, startY - 1, map) == false) {
           			direction = 0;
           		}
           	}
           	if (i == 7) {
           		if (isWalkable(startX, startY - 1, map) == false) {
           			direction = 0;
           		}
           	}
        }
        
        switch (direction) {
        case 0: System.out.println("0"); break; // nowhere
        case 1: curX--; curY++; System.out.println("1"); break; // top-left
        case 2: curY++; System.out.println("2"); break;          // top
        case 3: curX++; curY++; System.out.println("3"); break; // top-right
        case 4: curX--; System.out.println("4"); break;          // left
        case 5: curX++; System.out.println("5"); break;          // right
        case 6: curX--; curY--; System.out.println("6"); break; // bottom-left
        case 7: curY--; System.out.println("7"); break;          // bottom
        case 8: curX++; curY--; System.out.println("8"); break; // bottom-right
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
		System.out.println("Creature: " + creature.toString());
		System.out.println("Targets: " + possibleTargets.get(0).toString());
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
