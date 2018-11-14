package objects;

import logic.Game;

public abstract class Zombie extends GameObject {
	public Zombie(String name, String nameMsg, int x, int y, int vida, int harm, Game game, int speed) {
	    super(name, nameMsg, x, y, vida, harm, game, speed);
	}

	public int getSpeed() {
		return speed;
	}
	
	public void update() {
		boolean toca = (this.nacimiento % speed == this.game.getCiclos() % speed);
		boolean noPrimerCiclo = (this.nacimiento != this.game.getCiclos());
		boolean haAtacado = this.game.zombieAction(this, this.x, this.y);
		if (!haAtacado && !this.game.hayZombie(x, y-1) && noPrimerCiclo && toca){
			--this.y;
		}
	}
	
	public String toString(){
		return "Z [ " + this.getVida() + " ]";
	}
	
	public String toStringDebug(){
		return "Z[l:" + this.getVida() + ",x:" + this.x + ",y:" + this.y + ",t:" + (speed - ((this.game.getCiclos() - this.nacimiento) % speed)) +"]";
	}
}

