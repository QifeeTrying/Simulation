package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    public ArrayList<ArrayList<Character>> Grid;
    private List<Entity> List;
        
    public Map(int width, int height) {
    	Grid = new ArrayList<>();  
        List = new ArrayList<>();
        
        for (int i = 0; i < width; i++) {
            ArrayList<Character> row = new ArrayList<>();
            for (int j = 0; j < height; j++) {
                row.add('.');  // Fill with default values
            }
            Grid.add(row);
        }
        
    }
    
    public boolean addEntities(int count, Class<? extends Entity> type, Random rand, char Symbol) {
        
        for (int i = 0; i < count; i++) {
            int x, y;
            do {
                x = rand.nextInt(0, 10);
                y = rand.nextInt(0, 10);
            } while (getSymbolAt(x, y) != '.');
  
            Grid.get(x).set(y, Symbol);
          
        }
		return false;
    }

    
    public char getSymbolAt(int x, int y) {
        return Grid.get(x).get(y) != '\0' ? Grid.get(x).get(y) : '.';
    }

}
