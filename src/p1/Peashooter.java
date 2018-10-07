package p1;

public class Peashooter {
	private int x;
	private int y;
	private int vida;
	//private Game juego;
	
	public static int VIDA = 3;
	public static int HARM = 1;
	public static int COSTE = 50;
	
	
	public Peashooter(int x, int y, Game juego){
		this.x = x;
		this.y = y;
		this.vida = Peashooter.VIDA;
	//	this.juego = juego;
	}
	
	public int vida(){
		return this.vida;
	}
	
	public void serDanado(int dano){
		this.vida -= dano;
	}
	
	public int posx(){
		return this.x;
	}
	
	public int posy(){
		return this.y;
	}
}
