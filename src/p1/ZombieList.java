package p1;

public class ZombieList {
	private Zombie[] lista;
	private int cont;
	
	public ZombieList(){
		this.lista = new Zombie[32] ;
		this.cont = 0;
	}
	
	public void add(int x, int y, Game juego){
		this.lista[cont] = new Zombie(x,y, juego);
		++this.cont;
	}
	
	private Zombie getPosition(int x, int y) {
		boolean found = false;
		Zombie z = null;
		for (int i = 0; i < this.cont && !found; ++i){
			if (this.lista[i].vida() > 0 && this.lista[i].posx() == x && this.lista[i].posy() == y){
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
			if (this.lista[i].vida() > 0 && this.lista[i].posx() == x && this.lista[i].posy() == y)
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
            if (this.getvida(i)> 0){
                this.lista[i].update();
            }
        }
    }
    
	public String toString(int x, int y) {
		String sol = "";		
		if(this.hay(x, y)) 
			sol = this.lista[this.getIndex(x, y)].toString();
        return sol;
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
    
    public boolean todosMuertos() {
    	boolean sol = true;
    	for (int i = 0; i < this.cont && sol; ++i)
            if (this.lista[i].vida() > 0) sol = false;
    	return sol;
    }
}
