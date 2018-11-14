package objects;

import logic.Game;

public abstract class Plant extends GameObject {

	private final int cost;
	private final String symbol;
	
	public Plant(String name, String symbol, String nameMsg, int x, int y, int vida, int harm, int cost, Game game, int speed) {
	    super(name, nameMsg, x, y, vida, harm, game, speed);
	    this.cost = cost;
	    this.symbol = symbol;
	}

	public int getCost() {
		return cost;
	}
	
	public void update() {
	}

	public String getSymbol() {
		return symbol;
	}
	
	public String toString(){
		return symbol.toUpperCase() + " [ " + this.getVida() + " ]";
	}
	
}
