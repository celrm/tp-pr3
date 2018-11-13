package objects;

import logic.Game;

public class Peashooter extends Plant {

	public Peashooter(int x, int y, Game game) {
		super("peashooter", "p", "[P]eashooter", x, y, 5, 1, 50, game, 1);
		
	}
	
	public void update() {
		boolean toca = this.nacimiento % this.speed == this.game.getCiclos() % this.speed;
		if (toca) this.game.disparar(this,x, y);
	}

}
