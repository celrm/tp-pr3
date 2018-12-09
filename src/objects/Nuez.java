package objects;

public class Nuez extends Plant {

	public Nuez() {
		super("n", "nuez", "[N]uez", 10, 0, 0, 50);
	}
	
	public void update() {
	}
	public Plant parse(String PlantName) {
		boolean primeraletra = PlantName.equals(this.getSymbol());
		if(!PlantName.equals(this.getName()) && !primeraletra)
			return null;
		else return new Nuez();
	}
}
