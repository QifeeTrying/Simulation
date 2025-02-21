package src;

public abstract class Creature extends Entity {
    protected int hp;
    protected int speed;

    public Creature(int x, int y, int hp, int speed) {
        super(x, y);
        this.hp = hp;
        this.speed = speed;
    }

    public int getHP() {
    	return hp;
    	}
    
    public void takeDamage(int damage) { 
        hp -= damage; 
//        if (hp <= 0) die();
    }

    public abstract void makeMove(Map map);

}
