package objects;

public class Sunflower extends Plant {
	public static final int PRODUCE_SOLES = 10;
	
	public Sunflower() {
		super("sunflower", "s", "[S]unflower", 1, 0, 20, 2);		
	}
	
	public void update() {
		boolean toca = this.nacimiento % this.speed == this.game.getCiclos() % this.speed;
		boolean noPrimerCiclo = (this.nacimiento != this.game.getCiclos());
		if (toca && noPrimerCiclo) 
			game.generarSoles();
	}

}

