package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Map {
    public char[][] grid;
    private List<Entity> List;
        
    public Map(int width, int height) {
        grid = new char[10][10];
        List = new ArrayList<>();
    }

    public void addEntity(int x, int y, char symbol) {
        grid[x][y] = symbol;
    }
    
    public void addEntities(int count, Class<? extends Entity> type, Random rand, char Symbol) {
        
        for (int i = 0; i < count; i++) {
            int x, y;
            do {
                x = rand.nextInt(0, 10);
                y = rand.nextInt(0, 10);
            } while (getSymbolAt(x, y) != '.');
  
            addEntity(x, y, Symbol);
            
          
        }
    }

    
    public char getSymbolAt(int x, int y) {
        return grid[x][y] != '\0' ? grid[x][y] : '.';
    }
}
