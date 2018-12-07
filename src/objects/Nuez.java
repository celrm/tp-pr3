package objects;

import exceptions.CommandParseException;

public class Nuez extends Plant {

	public Nuez() {
		super("n", "nuez", "[N]uez", 10, 0, 0, 50);
	}
	
	public void update() {
		// Literalmente no hace nada.
		// Necesito esta función porque se supone que todas las plantas se actualizan.
		// También podría quitar el abstract del gameObject.update() y dejar ese vacío, y borrar éste.
	}
	public Plant parse(String PlantName) {
		boolean primeraletra = PlantName.equals(this.getSymbol());
		if(!PlantName.equals(this.getName()) && !primeraletra)
			return null;
		else return new Nuez();
	}
}
