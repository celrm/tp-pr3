package objects;

public abstract class Plant extends GameObject {
	private final int cost;
	
	public Plant(String name, int x, int y, int vida, int harm, int cost) {
	    super(name, x, y, vida, harm);
	    this.cost = cost;
	}

	public int getCost() {
		return cost;
	}
}
