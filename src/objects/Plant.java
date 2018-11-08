package objects;

import logic.Game;

public abstract class Plant extends GameObject {

	private final int cost;
	
	public Plant(String name, String nameMsg, int x, int y, int vida, int harm, int cost, Game game) {
	    super(name, nameMsg, x, y, vida, harm, game);
	    this.cost = cost;
	}

	public int getCost() {
		return cost;
	}
	
	public void update() {
	
	}
	
}
