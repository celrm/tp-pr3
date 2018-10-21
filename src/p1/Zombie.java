package p1;

public class Zombie {
	private int x;
	private int y;
	private int vida;
	private Game juego;
	
	private int nacimiento;
	
	public static final int VIDA = 5;
	public static final int HARM = 1;
	public static final int CICLOS = 2;
	
	public Zombie(int x, int y, Game juego) {
		this.x = x;
		this.y = y;
		this.vida = Zombie.VIDA;
		this.juego = juego;
		this.nacimiento = juego.getCiclos();
	}
	
	public void update() {
		if(this.vida > 0) {
			boolean toca = (this.nacimiento % CICLOS == this.juego.getCiclos() % CICLOS);
			boolean noPrimerCiclo = (this.nacimiento != this.juego.getCiclos());
			boolean haAtacado = this.juego.zombieAction(x, y);
			if (!haAtacado && !this.juego.hayZombie(x, y-1) && noPrimerCiclo && toca)
				--this.y;
		}
	}

	public void danar(int dano) {
		this.vida -= dano;
	}
	
	public int x() {
		return this.x;
	}
	
	public int y() {
		return this.y;
	}
	
	public int vida() {
		return this.vida;
	}
	
	public String toString() {
		if (this.vida > 0){
			return "Z [ " + this.vida + " ]";
		}
		return "";
	}
	
}
