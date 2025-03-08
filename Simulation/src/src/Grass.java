package src;

public class Grass extends Entity {
    public Grass(int x, int y, int ID, int hp) {
        super(x, y, ID, 1);
    }
    
    @Override
    public String toString() {
    	return "Grass ID " + ID + ", " + X + ", " + Y;
    }

}
