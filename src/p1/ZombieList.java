package p1;

public class ZombieList {
	private Zombie[] lista;
	private int cont;
	
	public ZombieList(){
		this.lista = new Zombie[32] ;
		this.cont = 0;
	}
	
	public void avanza (int pos){
		boolean alguien = false;
		
		int zx = lista[pos].posx();
		int zy = lista[pos].posy() - 1;
		
		for(int i = 0; i < this.cont && !alguien; ++i) {
			alguien = (lista[i].posx() == zx) && (lista[i].posy() == zy);
		}
		
		if(!alguien) this.lista[pos].avanza();
	}
	
	public int length(){
		return this.cont;
	}
	
	public void add(int x, int y, Game juego){
		this.lista[cont] = new Zombie(x,y, juego);
		++this.cont;
	}
	
	public void danar(int x, int y, int cant){
		for (int i = 0; i < this.cont; ++i){
			if (this.lista[i].vida() > 0 && this.lista[i].posx() == x && this.lista[i].posy() == y){
				this.lista[i].danar(cant);
			}
		}
	}
	
	public int posx(int pos){
		return this.lista[pos].posx();
	}
	
	public int posy(int pos){
		return this.lista[pos].posy();
	}
	
	public int getvida(int pos){
		return this.lista[pos].vida();
	}
}
