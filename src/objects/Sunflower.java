package objects;

import exceptions.CommandParseException;

public class Sunflower extends Plant {
	public static final int PRODUCE_SOLES = 10;
	
	public Sunflower() {
		super("s", "sunflower", "[S]unflower", 1, 0, 2, 20);		
	}
	
	public void update() {
		if (toca() && noPrimerCiclo()) 
			game.generarSoles();
	}
	public Plant parse(String PlantName) {
		boolean primeraletra = PlantName.equals(this.getSymbol());
		if(!PlantName.equals(this.getName()) && !primeraletra)
			return null;
		else return new Sunflower();
	}
}
