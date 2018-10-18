package p1;

public class SunflowerList {
	private Sunflower[] lista;
	private int cont;
	
	public SunflowerList(){
		this.lista = new Sunflower[32] ;
		this.cont = 0;
	}
	
	public void add(int x, int y, Game juego){
		this.lista[cont] = new Sunflower(x,y, juego);
		++this.cont;
	}
	
	private Sunflower getPosition(int x, int y) {
		boolean found = false;
		Sunflower s = null;
		for (int i = 0; i < this.cont && !found; ++i){
			if (this.lista[i].vida() > 0 && this.lista[i].x() == x && this.lista[i].y() == y){
				s = this.lista[i];
				found = true;
			}
		}
		return s;
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
		Sunflower s = getPosition(x,y);
		if(s != null) s.danar(cant);
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
		return this.lista[this.getIndex(x, y)].toString();
	}
}
