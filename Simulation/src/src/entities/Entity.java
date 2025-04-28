package src.entities;

import src.Map;

public abstract class Entity {
	protected int x, y, hp;
	protected final int id;

	public Entity(int x, int y, int id, int hp) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.hp = hp;
	}

	public int getX() { return x; }
	public int getY() { return y; }
	public int getID() { return id; }
	public int getHP() { return hp; }  

	public int getEntity() { return id; }

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void checkHealth(Map map) { 
		if (hp <= 0) map.markEntity(this.id);
	}

}
