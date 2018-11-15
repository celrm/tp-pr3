package objects;

public abstract class Zombie extends GameObject {
	public Zombie(String name, String nameMsg, int vida, int harm, int speed) {
	    super(name, nameMsg, vida, harm, speed);
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
		StringBuilder str = new StringBuilder();
		str.append("Z[l:").append(this.getVida());
		str.append(",x:").append(this.x);
		str.append(",y:").append(this.y);
		str.append(",t:").append(speed - ((this.game.getCiclos() - this.nacimiento) % speed));
		str.append("]");
		return str.toString();
	}
}

