package p1;

import java.util.Random;

public class ZombieManager {
    
	private int numZombies;
	private double frec;
	private Random rnd;
	
	public ZombieManager(Level level, Random rand){
		this.numZombies = level.numZombies();
		this.frec = level.frec();
		this.rnd = rand;
	}
	
	public boolean isZombieAdded() {
		if (this.numZombies > 0) {
			Double d = rnd.nextDouble();
			if (d < this.frec) {
				this.numZombies -= 1;
				return true;
			} else {
				return false;
			}
		}
		else return false;
	}
	
	public int numZombies() {
	    return this.numZombies;
	}
}
