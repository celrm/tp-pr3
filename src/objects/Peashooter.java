package objects;

public class Peashooter extends Plant {

	public Peashooter() {
		super("p", "peashooter", "[P]eashooter", 5, 1, 1, 50);
	}
	
	public void update() {
		if (toca()) this.game.disparar(this.x, this.y, this.harm);
	}
	public Plant parse(String PlantName) {
		boolean primeraletra = PlantName.equals(this.getSymbol());
		if(!PlantName.equals(this.getName()) && !primeraletra)
			return null;
		else return new Peashooter();
	}
}
