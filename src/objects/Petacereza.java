package objects;

import logic.Game;

public class Petacereza extends Plant {

	public Petacereza(int x, int y, Game game) {
		super("petacereza","Peta[c]ereza", x, y, 2, 10, 50, game,2);
		
	}
	
	public void update() {
		boolean toca = this.nacimiento % this.speed == this.game.getCiclos() % this.speed;
		boolean noPrimerCiclo = (this.nacimiento != this.game.getCiclos());
		if (toca && noPrimerCiclo) game.explotar(this);
	}

}
