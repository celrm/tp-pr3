package objects;


public class Peashooter extends Plant {

	public Peashooter() {
		super("peashooter", "p", "[P]eashooter", 5, 1, 50, 1);
		
	}
	
	public void update() {
		boolean toca = this.nacimiento % this.speed == this.game.getCiclos() % this.speed;
		if (toca) this.game.disparar(this,x, y);
	}

}
