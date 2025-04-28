package src;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import src.entities.Entity;
import src.entities.Grass;
import src.entities.Herbivore;
import src.entities.Predator;
import src.entities.Rock;
import src.entities.Tree;
import java.util.List;
import java.util.Random;

public class Map {
	public List<Entity> list;
	{
		list = new ArrayList<>();
	}
	ArrayList<Integer> marked = new ArrayList<>(Collections.nCopies(20, 0));
	ArrayList<Integer> alive = new ArrayList<>(Collections.nCopies(20, 0));
    private static int globalID = 0;
	Random rand = new Random();

	public boolean initialaddEntities(int count, Class<? extends Entity> type, Random rand, char Symbol) {
		for (int i = 0; i < count; i++) {
			int x, y;

			do {
				x = rand.nextInt(10);
				y = rand.nextInt(10);
			} while (isOccupied(x, y));

			try {
				Entity entity = type.getDeclaredConstructor(int.class, int.class, int.class, int.class).newInstance(x, y,  globalID++, 20);
				alive.set(globalID - 1, 1);
				list.add(entity);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
    
	public boolean addEntities(int count, Class<? extends Entity> type, Random rand, char Symbol, int ID) {
		for (int i = 0; i < count; i++) {
			int x, y;

			do {
				x = rand.nextInt(10);
				y = rand.nextInt(10);
			} while (isOccupied(x, y));

			try {
				Entity entity = type.getDeclaredConstructor(int.class, int.class, int.class, int.class).newInstance(x, y, ID, 20);
				alive.set(ID, 1);
				list.add(entity);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}

	public boolean isOccupied(int x, int y) {
		for (Entity e : list) {
			if (e.getX() == x && e.getY() == y) {
				return true;
			}
		}
		return false;
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
		return list;
	}

	public List<Grass> getGrass() {
		return list.stream()
				.filter(entity -> entity instanceof Grass)
				.map(entity -> (Grass) entity)
				.collect(Collectors.toList());
	}

	public List<Herbivore> getHerbivores() {
		return list.stream()
				.filter(entity -> entity instanceof Herbivore)
				.map(entity -> (Herbivore) entity)
				.collect(Collectors.toList());
	}


	public void markEntity(int ID) {
		marked.set(ID, ID);
	}
	
	public void removeEntity() {
		for (int i = 0; i < 20; i++)
		{
			if (marked.get(i) > 0)
			{
				int removeID = marked.get(i);
				list.removeIf(entity -> entity.getID() == removeID);
				marked.set(i, 0);
				alive.set(i, 0);
			}
		}
	}
	
	public void checkEntityAlive() {
		for (int i = 0; i < 20; i++)
		{
			if (alive.get(i) == 0)
			{
				if (i >= 0 && i < 5)
				{
					addEntities(1, Herbivore.class, rand, 'H', i);
				}
				if (i > 4 && i < 7)
				{
					addEntities(1, Predator.class, rand, 'P', i);
				}
				if (i > 6 && i < 14)
				{
					addEntities(1, Grass.class, rand, 'G', i);
				}
				if (i > 13 && i < 17)
				{
					addEntities(1, Rock.class, rand, 'R', i);
				}
				if (i > 16 && i < 20)
				{
					addEntities(1, Tree.class, rand, 'T', i);
				}

			}
		}
	}

	public Entity getEntityAt(int x, int y) {
		for (Entity entity : list) {
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
