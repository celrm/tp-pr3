package objects;

public abstract class Zombie extends GameObject {
  private final int speed;
  
	public Zombie(String name, String nameMsg, int x, int y, int vida, int harm, int speed) {
	    super(name, nameMsg, x, y, vida, harm);
	    this.speed = speed;
	}

	public int getSpeed() {
		return speed;
	}
}

