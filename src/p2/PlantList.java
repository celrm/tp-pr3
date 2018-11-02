package p2;

public class PlantList {
	private Plant[] lista;
	private int cont;
	
	public PlantList(){
		this.lista = new Plant[32] ;
		this.cont = 0;
	}
	
	public void add(int x, int y, Game juego){
		this.lista[cont] = new Plant(x,y, juego);
		++this.cont;
	}
	
	private Zombie getPosition(int x, int y) {
		boolean found = false;
		Zombie z = null;
		for (int i = 0; i < this.cont && !found; ++i){
			if (this.lista[i].vida() > 0 && this.lista[i].x() == x && this.lista[i].y() == y){
				z = this.lista[i];
				found = true;
			}
		}
		return z;
	}
	
	public int getIndex(int x, int y) {
		boolean found = false;
		int i = 0;
		while (i < this.cont && !found){
			if (this.lista[i].vida() > 0 && this.lista[i].x() == x && this.lista[i].y() == y)
				found = true;
			else ++i;
		}
		if (found) return i;
		else return -1;
	}
	
	public void danar(int x, int y, int cant){
		Zombie z = getPosition(x,y);
		if(z != null) z.danar(cant);
	}
	
	public boolean hay(int x, int y){
        return getPosition(x,y) != null;
    }
	
	public void update(){
        for (int i = 0; i < this.cont; ++i){
                this.lista[i].update();
        }
    }
    
	public String toString(int x, int y) {
		if (this.hay(x, y)){
			return this.lista[this.getIndex(x, y)].toString();
		}
		return "";
	}
}
