package objects;

public abstract class Zombie extends GameObject {
	
	public Zombie(String name, String nameMsg, int vida, int harm, int speed) {
	    super("z", name, nameMsg, vida, harm, speed);
	}
	
	public void update() {
		boolean haAtacado = this.game.zombieAction(this.harm, this.x, this.y);
		if (!haAtacado && !this.game.hayZombie(x, y-1) && noPrimerCiclo() && toca())
			--this.y;
	}
	
	public String listMsg(StringBuilder sol){
		sol.append(this.getNameMsg()).append(":");
		sol.append(" Speed: ").append(Integer.toString(this.getSpeed()));
		sol.append(" Harm: ").append(Integer.toString(this.getHarm())); 
		sol.append(" Life: ").append(Integer.toString(this.getVida())).append("\n");
		return sol.toString();
	}
}
