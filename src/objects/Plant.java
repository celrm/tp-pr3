package objects;

import exceptions.CommandParseException;


public abstract class Plant extends GameObject {

	private final int cost;
	
	public Plant(String symbol, String name, String nameMsg, int vida, int harm, int speed, int cost) {
	    super(symbol, name, nameMsg, vida, harm, speed);
	    this.cost = cost;
	}

	public int getCost() {
		return cost;
	}
	
	public String listMsg(StringBuilder sol){
		sol.append(this.getNameMsg()).append(":");
		sol.append(" Cost: ").append(Integer.toString(this.getCost())).append(" suncoins");
		sol.append(" Harm: ").append(Integer.toString(this.getHarm())).append("\n");
		return sol.toString();
	}
	public Plant parse(String PlantName) throws CommandParseException {
		boolean primeraletra = PlantName.equals(this.getSymbol());
		if(!PlantName.equals(this.getName()) && !primeraletra)
			return null;
		else return this;
	}
}
