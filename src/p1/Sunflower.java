package p1;

public class Sunflower {
	private int x;
	private int y;
	private int vida;
	private Game juego;
	
	private int nacimiento;
	
	public Sunflower(int x, int y, Game juego) {
		this.x = x;
		this.y = y;
		this.vida = 1;
		this.juego = juego;
		this.nacimiento = juego.ciclos();
	}
	
	public int vida() {
		return this.vida;
	}
	
	public void serDanado(int dano) {
		this.vida -= dano;
	}
	
	public void generarSoles() {
		if (this.nacimiento % 2 == this.juego.ciclos() % 2)
			this.juego.cambiarSoles(10);
	}
	
	public int posx(){
		return this.x;
	}
	public int posy(){
		return this.y;
	}
}
