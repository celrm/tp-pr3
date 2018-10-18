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
	
	public void add(int x, int y, Game juego){
		this.lista[cont] = new Peashooter(x,y, juego);
		++this.cont;
	}
	
	public boolean danar(int x, int y, int cant){
		boolean alguien = false;
		for (int i = 0; i < this.cont && !alguien; ++i){
			if (this.lista[i].vida() > 0 && this.lista[i].posx() == x && this.lista[i].posy() == y){
				this.lista[i].danar(cant);
				alguien = true;
			}
		}
		return alguien;
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
    public boolean hay(int x, int y){
        boolean hay = false;
        for (int i = 0; i < this.cont && !hay; ++i){
            if (this.lista[i].posx() == x && this.lista[i].posy() == y){
                hay = true;
            }
        }
        return hay;
    }
    
    public String toString(int x, int y) {
		String sol = "";
		boolean found = false;
        for (int i = 0; i < this.cont && !found; ++i){
            if (this.lista[i].posx() == x && this.lista[i].posy() == y){
            	found = true;
            	sol = this.lista[i].toString();
            }
        }
        return sol;
	}
}
