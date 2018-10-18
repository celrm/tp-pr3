package p1;

public class ZombieList {
	private Zombie[] lista;
	private int cont;
	
	//private Sunflower getPosition(int x, int y) {}	
	
	public ZombieList(){
		this.lista = new Zombie[32] ;
		this.cont = 0;
	}
	
	public void add(int x, int y, Game juego){
		this.lista[cont] = new Zombie(x,y, juego);
		++this.cont;
	}
	
	//doDamage
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
    
    public void update(){
        for (int i = 0; i < this.cont; ++i){
            if (this.getvida(i)> 0){
                this.lista[i].update();
            }
        }
    }
    
	public String toString(int x, int y) {
		String sol = "";
		boolean found = false;
        for (int i = 0; i < this.cont && !found; ++i){
            if (this.lista[i].vida() > 0 && this.lista[i].posx() == x && this.lista[i].posy() == y){
            	found = true;
            	sol = this.lista[i].toString();
            }
        }
        return sol;
	}
    
    public boolean hay(int x, int y){
        boolean hay = false;
        for (int i = 0; i < this.cont && !hay; ++i){
            if (this.lista[i].vida() > 0 && this.lista[i].posx() == x && this.lista[i].posy() == y){
                hay = true;
            }
        }
        return hay;
    }
    
    public boolean todosMuertos() {
    	boolean sol = true;
    	for (int i = 0; i < this.cont && sol; ++i)
            if (this.lista[i].vida() > 0) sol = false;
    	return sol;
    }
}
