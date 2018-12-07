package logic;

import java.util.Random;

public class ZombieManager {
    
	private int remZombies;
	private double frec;
	private Random rand;
	
	public ZombieManager(Level level, Random rand){
		this.remZombies = level.getNumberOfZombies();
		this.frec = level.getZombieFrequency();
		this.rand = rand;
	}
	
	public boolean isZombieAdded() {
		if (this.remZombies > 0) {
			Double d = rand.nextDouble();
			if (d < this.frec) {
				this.remZombies -= 1;
				return true;
			} else {
				return false;
			}
		}
		else return false;
	}
	
	public int getRemZombies() {
	    return this.remZombies;
	}
	public void setRemZombies(int num) {
	    this.remZombies = num;
	}
}
