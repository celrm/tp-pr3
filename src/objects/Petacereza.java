package objects;


public class Petacereza extends Plant {

	public Petacereza() {
		super("petacereza", "c", "Peta[c]ereza", 2, 10, 50, 2);
		
	}
	
	public void update() {
		boolean toca = this.nacimiento % this.speed == super.game.getCiclos() % this.speed;
		boolean noPrimerCiclo = (this.nacimiento != this.game.getCiclos());
		if (toca && noPrimerCiclo) {
			this.game.explotar(this);
			this.danar(this.getVida());
		}
	}

}
