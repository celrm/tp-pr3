package p1;

import java.util.Random;

public class ZombieManager {
    
	private int numZombies;
	private double frec;
	private Random rnd;
	
	public ZombieManager(Level level){
		this.numZombies = level.numZombies();
		this.frec = level.frec();
		this.rnd = new Random();
	}
	
	public boolean isZombieAdded(Level level) {
		Double d = rnd.nextDouble();
		if (d < this.frec) {
			this.numZombies -= 1;
			return true;
		} else {
			return false;
		}
		
	}
	
	public boolean remZombies() {
	    return numZombies;
	}
}
