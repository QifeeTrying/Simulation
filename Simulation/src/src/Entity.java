package src;

public abstract class Entity {
    protected int X, Y, HP;
    protected final int ID;

    public Entity(int x, int y, int ID, int HP) {
        this.X = x;
        this.Y = y;
        this.ID = ID;
        this.HP = HP;
    }

    public int getX() { return X; }
    public int getY() { return Y; }
    public int getID() { return ID; }  
    
    public int getEntity() { return ID; }
    
    public void setPosition(int x, int y) {
        this.X = x;
        this.Y = y;
    }
    
    public void checkHealth(Map map) { 
        if (HP <= 0) map.removeEntity(this.ID);
    }
    
    public void damage(int x, int y, Map map) {
        Entity target = map.getEntityAt(x, y);
        if (target != null) {
        target.HP -= 10;
        }
    }
    
    @Override
    public String toString() {super.toString();}

}
