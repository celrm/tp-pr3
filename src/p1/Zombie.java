package p1;

public class Zombie {
	private int x;
	private int y;
	private int vida;
	private Game juego;
	
	public static int VIDA = 5;

	private int nacimiento;
	
	public Zombie(int x, int y, Game juego){
		this.x = x;
		this.y = y;
		this.vida = Zombie.VIDA;
		this.juego = juego;
		this.nacimiento = juego.ciclos();
	}
	
	public int vida(){
		return this.vida;
	}
	public void serDanado(int dano){
		this.vida -= dano;
	}
	
	public void avanza(){
		if (this.nacimiento % 2 == this.juego.ciclos() % 2){
			-- this.y;
		}
	}
	public int posx(){
		return this.x;
	}
	public int posy(){
		return this.y;
	}
}
