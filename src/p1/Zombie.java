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
		boolean attac = this.juego.zombieAction(x, y);
		boolean tocaAvanzar = (this.nacimiento % CICLOS == this.juego.getCiclos() % CICLOS) && (this.nacimiento != this.juego.getCiclos());
		if (!attac && !this.juego.hayZombie(x, y-1) && tocaAvanzar)
			--this.y;
	}

	public void danar(int dano) {
		this.vida -= dano;
	}
	
	public int posx() {
		return this.x;
	}
	
	public int posy() {
		return this.y;
	}
	
	public int vida() {
		return this.vida;
	}
	
	public String toString() {
		return "Z [ " + this.vida + " ]";
	}
	
}
