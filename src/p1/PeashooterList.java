package p1;

public class PeashooterList {
	private Peashooter[] lista;
	private int cont;
	
	public PeashooterList(){
		this.lista = new Peashooter[32] ;
		this.cont = 0;
	}
	
	public void add(int x, int y, Game juego){
		this.lista[cont] = new Peashooter(x,y, juego);
		++this.cont;
	}
	
	private Peashooter getPosition(int x, int y) {
		boolean found = false;
		Peashooter p = null;
		for (int i = 0; i < this.cont && !found; ++i){
			if (this.lista[i].vida() > 0 && this.lista[i].x() == x && this.lista[i].y() == y){
				p = this.lista[i];
				found = true;
			}
		}
		return p;
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
		Peashooter p = getPosition(x,y);
		if(p != null) p.danar(cant);
	}
	
	public boolean hay(int x, int y){
        return getPosition(x,y) != null;
    }
    
    public void update(){
        for (int i = 0; i < this.cont; ++i){
            if (this.lista[i].vida() > 0){
                this.lista[i].update();
            }
        }
    }
    
	public String toString(int x, int y) {
		if (this.hay(x, y)){
			return this.lista[this.getIndex(x, y)].toString();
		}
		return "";
	}
}
