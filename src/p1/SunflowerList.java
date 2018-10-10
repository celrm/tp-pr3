package p1;

public class SunflowerList {
	private Sunflower[] lista;
	private int cont;
	
	public SunflowerList(){
		this.lista = new Sunflower[32] ;
		this.cont = 0;
	}
	
	public int length(){
		return this.cont;
	}
	
	public Sunflower lista(int pos){
		return this.lista[pos];
	}
	
	public void addSunflower (int x, int y, Game juego){
		this.lista[cont] = new Sunflower(x,y, juego);
		++this.cont;
	}
	
	public boolean danar(int x, int y, int cant){
		boolean alguien = false;
		for (int i = 0; i < this.cont && !alguien; ++i){
			if (this.lista[i].vida() > 0 && this.lista[i].posx() == x && this.lista[i].posy() == y){
				this.lista[i].serDanado(cant);
				alguien = true;
			}
		}
		return alguien;
	}
	
	public int posx (int pos){
		return this.lista[pos].posx();
	}
	
	public int posy (int pos){
		return this.lista[pos].posy();
	}
	
	public int getvida(int pos){
		return this.lista[pos].vida();
	}
	
	public int generarSoles() {
		int sol = 0;
		for (int i = 0; i < this.cont; ++i)
			if(lista[i].vida() > 0 && lista[i].tocaGenerar()) 
				sol++;
		return sol;
	}
	
}

