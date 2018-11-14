package lists;

import objects.GameObject;

public class ObjectList {
	private GameObject[] lista;
	private int cont;
	
	public ObjectList(){
		this.lista = new GameObject[10];
		this.cont = 0;
	}

	public int getCont() {
		return this.cont;
	}
	
	public void add(GameObject object){
		this.lista[cont] = object;
		++this.cont;
	}
	
	// TODO resize

	private GameObject getPosition(int x, int y){
		boolean found = false;
		GameObject o = null;
		for (int i = 0; i < this.cont && !found; ++i){
			if (this.lista[i].getVida() > 0 && this.lista[i].x() == x && this.lista[i].y() == y){
				o = this.lista[i];
				found = true;
			}
		}
		return o;
	}
	
	
	public int getIndex(int x, int y) {
		boolean found = false;
		int i = 0;
		while (i < this.cont && !found){
			if (this.lista[i].getVida() > 0 && this.lista[i].x() == x && this.lista[i].y() == y)
				found = true;
			else ++i;
		}
		if (found) return i;
		else return -1;
	}
	
	public void danar(int x, int y, int cant){
		GameObject o = getPosition(x,y);
		if(o != null) o.danar(cant);
	}
	
	public boolean hay(int x, int y){
        return getPosition(x,y) != null;
    }
	
	private void remove(){
		int vivo = 0;
		for (int i = 0; i < this.cont; ++i){
			if (this.lista[i].getVida()>0){
				this.lista[vivo] = this.lista[i];
				++vivo;
			}
		}
		this.cont = vivo;

	}
	
	public void update(){
        for (int i = 0; i < this.cont; ++i){
                this.lista[i].update();
        }
        this.remove();        
        
    }
    
	public String toString(int x, int y) {
		if (this.hay(x, y)){
			return this.lista[this.getIndex(x, y)].toString();
		}
		return "";
	}
	
	public String toStringDebug(int x, int y) {
		if (this.hay(x, y)){
			return this.lista[this.getIndex(x, y)].toStringDebug();
		}
		return "";
	}
}
