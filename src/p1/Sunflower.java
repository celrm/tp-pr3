package p1;

public class Sunflower {
	private int x;
	private int y;
	private int vida;
	private Game juego;
	
	private int nacimiento;
	
	public static final int VIDA = 1;
	public static final int HARM = 0;
	public static final int PRODUCE_SOLES = 10;
	public static final int COSTE = 20;
	public static final int CICLOS = 2;
	
	public Sunflower(int x, int y, Game juego) {
		this.x = x;
		this.y = y;
		this.vida = Sunflower.VIDA;
		this.juego = juego;
		this.nacimiento = juego.getCiclos();
	}
	
	public void update() {
		if ((this.nacimiento % CICLOS == this.juego.getCiclos() % CICLOS) && (this.nacimiento != this.juego.getCiclos()))
			this.juego.sunflowerAction();
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
			return "S [ " + this.vida + " ]";
		}
		return "";
	}
}
	

