package src;

public abstract class Entity {
    protected int X, Y;

    public Entity(int x, int y) {
        this.X = x;
        this.Y = y;
    }

    public int getX() { return X; }
    public int getY() { return Y; }
    
    public void setPosition(int x, int y) {
        this.X = x;
        this.Y = y;
    }
}
