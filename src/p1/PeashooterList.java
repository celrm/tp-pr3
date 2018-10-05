package p1;

public class PeashooterList {
	private Peashooter[] lista;
	private int cont;
	
	public PeashooterList(){
		this.lista = new Peashooter[32] ;
		this.cont = 0;
	}
	
	public int length(){
		return this.cont;
	}
	
	public Peashooter lista(int pos){
		return this.lista[pos];
	}
	public void addPeashooter (int x, int y, Game juego){
		this.lista[cont] = new Peashooter(x,y, juego);
		++this.cont;
	}
	
	
}
