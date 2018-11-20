package objects;

public class Nuez extends Plant {

	public Nuez() {
		super("n", "nuez", "[N]uez", 10, 0, 0, 50);
	}
	
	public void update() {
		// Literalmente no hace nada.
		// Necesito esta función porque se supone que todas las plantas se actualizan.
		// También podría quitar el abstract del gameObject.update() y dejar ese vacío, y borrar éste.
	}

}
