package src;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Map {
	private HashMap<String, Character> Grid;
    private List<Entity> List;
    private List<Herbivore> herbivores;
    private List<Predator> predators;
    private List<Entity> grass;
        
    public Map(int width, int height) {
    	this.Grid = new HashMap<>();  
        List = new ArrayList<>();
        this.herbivores = new ArrayList<>();
        this.predators = new ArrayList<>();
        this.grass = new ArrayList<>();

    }
    
    public boolean addEntities(int count, Class<? extends Entity> type, Random rand, char Symbol) {
        for (int i = 0; i < count; i++) {
            int x, y;
            do {
                x = rand.nextInt(0, 10);
                y = rand.nextInt(0, 10);
            } while (getSymbolAt(x, y) != '.');

            Grid.put(x + "," + y, Symbol);
            
            if (type == Herbivore.class) {
                Herbivore h = new Herbivore(x, y);
                herbivores.add(h);
            } else if (type == Predator.class) {
                Predator p = new Predator(x, y);
                predators.add(p);
            } else if (type == Grass.class) {
                Grass g = new Grass(x, y);
                grass.add(g);
            }
        }
        return true;
    }

    public boolean addEntity(Class<? extends Entity> type, int PositionX, int PositionY) {
            int x = PositionX, y = PositionY;
            if (type == Herbivore.class) {
            	char Symbol = 'H';
            	Grid.put(x + "," + y, Symbol);
                Herbivore h = new Herbivore(x, y);
                System.out.println(Symbol);
                herbivores.add(h);
            } else if (type == Predator.class) {
            	char Symbol = 'P';
            	Grid.put(x + "," + y, Symbol);
                Predator p = new Predator(x, y);
                predators.add(p);
            }
        return true;
    }

    
    public char getSymbolAt(int x, int y) {
        return Grid.getOrDefault(x + "," + y, '.');
    }
    
    public boolean isValid(int x, int y) {
        return x >= 0 && x < 10 && y >= 0 && y < 10;
    }
    
    public List<Herbivore> getHerbivores() {
        return herbivores;
    }

    public List<Predator> getPredators() {
        return predators;
    }

    public List<Entity> getGrass() {
        return grass;
    }
    
    public void removeEntity(int x, int y) {
        Grid.remove(x + "," + y);
        herbivores.removeIf(h -> h.getX() == x && h.getY() == y);     
        predators.removeIf(p -> p.getX() == x && p.getY() == y);
    }


}
