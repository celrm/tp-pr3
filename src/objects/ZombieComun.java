package objects;

import logic.Game;
import objects.Zombie;

public class ZombieComun extends Zombie {

	public ZombieComun(int x, int y, Game game) {
		super("Zombie comun","[Z]ombie comun", x, y, 5, 1,game, 2);
	}

}
