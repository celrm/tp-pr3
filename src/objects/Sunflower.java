package objects;

import logic.Game;

public class Sunflower extends Plant {
	public static final int PRODUCE_SOLES = 10;
	public Sunflower(int x, int y, Game game) {
		super("sunflower", "[S]unflower", x, y, 1, 0, 20, game, 2);
		
	}
	
	public void update() {
		boolean toca = this.nacimiento % this.speed == this.game.getCiclos() % this.speed;
		boolean noPrimerCiclo = (this.nacimiento != this.game.getCiclos());
		if (toca && noPrimerCiclo) game.generarSoles();
	}

}

