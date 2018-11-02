package logic;

import java.util.Random;

public class ZombieManager {
    
	private int numZombies;
	private double frec;
	private Random rand;
	
	public ZombieManager(Level level, Random rand){
		this.numZombies = level.numZombies();
		this.frec = level.frec();
		this.rand = rand;
	}
	
	public boolean isZombieAdded() {
		if (this.numZombies > 0) {
			Double d = rand.nextDouble();
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
