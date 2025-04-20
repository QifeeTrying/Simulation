package src.entities;

public class Grass extends Entity {
    public Grass(int x, int y, int id, int hp) {
        super(x, y, id, 1);
    }
    
    @Override
    public String toString() {
    	return "Grass ID " + id + ", " + x + ", " + y;
    }

}
