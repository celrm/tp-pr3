package objects;

import logic.Game;
import objects.Zombie;

public class ZombieDeportista extends Zombie {

	public ZombieDeportista(int x, int y, Game game) {
		super("Zombie deportista", "[Z]ombie deportista", x, y, 2, 1, 1, game);
	}

}
