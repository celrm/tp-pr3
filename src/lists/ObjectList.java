package lists;

import java.io.BufferedWriter;
import java.io.IOException;

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
		if (this.cont == this.lista.length){
			this.resize();
		}
		this.lista[cont] = object;
		++this.cont;
	}
	
	// al doble
	private void resize(){
		GameObject[] aux = this.lista;
		this.lista = new GameObject[aux.length * 2];
		for (int i = 0; i < aux.length; ++i){
			this.lista[i] = aux[i];
		}
	}
	

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
	

	public void remove(){
		GameObject[] aux = this.lista;
		this.lista = new GameObject[aux.length];
		int j  =  0;
		for(int i = 0; i < this.cont; ++i){
			if (aux[i].getVida() > 0){
				this.lista[j] = aux[i];
				++j;
			}
		}
		this.cont = j;

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
	
	public String toStringDebug(int i) {
		return this.lista[i].toStringDebug();
	}
	
	public void store(BufferedWriter outStream) throws IOException{
		for (int i = 0; i < this.cont;++i){
			if (i != 0)
				outStream.write(", ");
			this.lista[i].store(outStream);
		}
	}
}
