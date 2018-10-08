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
		Sunflower r = this.lista[pos].clone();
		return r;
	}
	
	public void addSunflower (int x, int y, Game juego){
		this.lista[cont] = new Sunflower(x,y, juego);
		++this.cont;
	}
	
	public void danar(int pos, int cant){
		this.lista[pos].serDanado(cant);
	}
}
