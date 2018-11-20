package objects;


public abstract class Plant extends GameObject {

	private final int cost;
	private final String symbol;
	
	public Plant(String name, String symbol, String nameMsg, int vida, int harm, int cost, int speed) {
	    super(name, nameMsg, vida, harm, speed);
	    this.cost = cost;
	    this.symbol = symbol;
	}

	public int getCost() {
		return cost;
	}

	public String getSymbol() {
		return symbol;
	}
	
	public String toString(){
		return symbol.toUpperCase() + " [ " + this.getVida() + " ]";
	}
	
	public String toStringDebug(){
		StringBuilder str = new StringBuilder();
		str.append(symbol.toUpperCase()).append("[l:").append(this.getVida());
		str.append(",x:").append(this.x);
		str.append(",y:").append(this.y);
		str.append(",t:").append(speed - ((this.game.getCiclos() - this.nacimiento) % speed));
		str.append("]");
		return str.toString();
	}
}
