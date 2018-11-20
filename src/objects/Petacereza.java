package objects;


public class Petacereza extends Plant {

	public Petacereza() {
		super("c", "petacereza", "Peta[c]ereza", 2, 10, 2, 50);	
	}
	
	public void update() {
		boolean toca = this.nacimiento % this.speed == this.game.getCiclos() % this.speed;
		boolean noPrimerCiclo = (this.nacimiento != this.game.getCiclos());
		if (toca && noPrimerCiclo) {
			this.game.explotar(this.x, this.y, this.harm);
			this.danar(this.getVida());
		}
	}

}
