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
	
	public void danar(int x, int y, int cant){
		for (int i = 0; i < this.cont; ++i){
			if (this.lista[i].vida() > 0 && this.lista[i].posx() == x && this.lista[i].posy() == y){
				this.lista[i].serDanado(cant);
			}
		}
	}
}
