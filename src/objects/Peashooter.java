package objects;

public class Peashooter extends Plant {

	public Peashooter() {
		super("p", "peashooter", "[P]eashooter", 5, 1, 1, 50);
	}
	
	public void update() {
		boolean toca = this.nacimiento % this.speed == this.game.getCiclos() % this.speed;
		if (toca) this.game.disparar(this.x, this.y, this.harm);
	}

}
