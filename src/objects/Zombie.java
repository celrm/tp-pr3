package objects;

import logic.Game;

public abstract class Zombie extends GameObject {
  private final int speed;
  
	public Zombie(String name, String nameMsg, int x, int y, int vida, int harm, int speed, Game game) {
	    super(name, nameMsg, x, y, vida, harm, game);
	    this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}
	
	public void update() {
		
	}
}

