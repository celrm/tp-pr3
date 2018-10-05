package p1;

public class ZombieList {
	private Zombie[] lista;
	private int cont;
	
	public ZombieList(){
		this.lista = new Zombie[32] ;
		this.cont = 0;
	}
	
	public int length(){
		return this.cont;
	}
	
	public Zombie lista(int pos){
		return this.lista[pos];
	}
	public void addZombie (int x, int y, Game juego){
		this.lista[cont] = new Zombie(x,y, juego);
		++this.cont;
	}
}
