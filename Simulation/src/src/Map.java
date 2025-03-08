package src;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map {
	public HashMap<String, Character> Grid;
    private List<Entity> List;
    private static int globalID = 1;

    public Map(int width, int height) {
    	this.Grid = new HashMap<>();  
        List = new ArrayList<>();
    }
    
    public boolean addEntities(int count, Class<? extends Entity> type, Random rand, char Symbol) {
        for (int i = 0; i < count; i++) {
            int x, y;

            do {
                x = rand.nextInt(10);
                y = rand.nextInt(10);
            } while (isOccupied(x, y));

            try {
            	Entity entity = type.getDeclaredConstructor(int.class, int.class, int.class, int.class).newInstance(x, y, globalID++, 20);
            	List.add(entity);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
 
    private boolean isOccupied(int x, int y) {
        for (Entity e : List) {
            if (e.getX() == x && e.getY() == y) {
                return true;
            }
        }
        return false;
    }

    
    public void addEntity(Entity entity) {
        List.add(entity);
        Grid.put(entity.getX() + "," + entity.getY(), getSymbolForEntity(entity));
    }
    
    public char getSymbolForEntity(Entity entity) {
        if (entity instanceof Grass) return 'G';
        if (entity instanceof Herbivore) return 'H';
        if (entity instanceof Predator) return 'P';
        if (entity instanceof Rock) return 'R';
        if (entity instanceof Tree) return 'T';
        return '?';
    }    
    
    public List<Entity> getEntities() {
        return List;
    }
    
    public List<Grass> getGrass() {
        return List.stream()
                   .filter(entity -> entity instanceof Grass)
                   .map(entity -> (Grass) entity)
                   .collect(Collectors.toList());
    }

    public List<Herbivore> getHerbivores() {
        return List.stream()
                   .filter(entity -> entity instanceof Herbivore)
                   .map(entity -> (Herbivore) entity)
                   .collect(Collectors.toList());
    }

    
    public void removeEntity(int ID) {
        List.removeIf(entity -> entity.getID() == ID);
    }

    public Entity getEntityAt(int x, int y) {
        for (Entity entity : List) {
            if (entity.getX() == x && entity.getY() == y) {
                return entity;
            }
        }
        return null;
    }
    
    public boolean isValid(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }


}
