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
	
	public void avanza (int pos){
		this.lista[pos].avanza();
	}
	public void addZombie (int x, int y, Game juego){
		this.lista[cont] = new Zombie(x,y, juego);
		++this.cont;
	}
	//public void danar(int pos){
	//	this.lista[pos].serDanado(1);
	//}
}
