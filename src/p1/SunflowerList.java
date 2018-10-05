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
}
