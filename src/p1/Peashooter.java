package p1;

public class Peashooter {
	private int x;
	private int y;
	private int vida;
	private Game juego;
	
	private int nacimiento;
	
	public static final int VIDA = 3;
	public static final int HARM = 1;
	public static final int COSTE = 50;
	public static final int CICLOS = 1;
	
	public Peashooter(int x, int y, Game juego) {
		this.x = x;
		this.y = y;
		this.vida = Peashooter.VIDA;
		this.juego = juego;
		this.nacimiento = juego.getCiclos();
	}
	
	public void update() {
		if ((this.nacimiento % CICLOS == this.juego.getCiclos() % CICLOS))
			this.juego.peashooterAction(x, y);
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
		return "P [ " + this.vida + " ]";
	}
	
}
