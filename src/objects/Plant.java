package objects;

import logic.Game;

public abstract class Plant extends GameObject {

	private final int cost;
	
	public Plant(String name, String nameMsg, int x, int y, int vida, int harm, int cost, Game game, int speed) {
	    super(name, nameMsg, x, y, vida, harm, game, speed);
	    this.cost = cost;
	}

	public int getCost() {
		return cost;
	}
	
	public void update() {
		boolean toca = (this.nacimiento % speed == this.game.getCiclos() % speed);
	}
	
}
