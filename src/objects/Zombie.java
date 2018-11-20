package objects;

public abstract class Zombie extends GameObject {
	
	public Zombie(String name, String nameMsg, int vida, int harm, int speed) {
	    super("z", name, nameMsg, vida, harm, speed);
	}
	
	public void update() {
		boolean toca = (this.nacimiento % speed == this.game.getCiclos() % speed);
		boolean noPrimerCiclo = (this.nacimiento != this.game.getCiclos());
		boolean haAtacado = this.game.zombieAction(this.harm, this.x, this.y);
		if (!haAtacado && !this.game.hayZombie(x, y-1) && noPrimerCiclo && toca)
			--this.y;
	}
}
