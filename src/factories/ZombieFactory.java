package factories;

import exceptions.CommandParseException;
import objects.Plant;
import objects.Zombie;
import objects.ZombieCaracubo;
import objects.ZombieComun;
import objects.ZombieDeportista;

public class ZombieFactory {
	private static Zombie[] availableZombies = {
		new ZombieComun(),
		new ZombieDeportista(),
		new ZombieCaracubo()
	};

	public static Zombie getZombie(String zombieName) throws CommandParseException {
		Zombie z = null;
		for (Zombie item : availableZombies) {
			if(z==null)
				z = item.parse(zombieName);
		}
		if (z == null){
			throw new CommandParseException("Unknown zombie name: " + zombieName);
		}
		return z;
	}
	
	public static String listOfAvailableZombies() {
		String sol = null;
		for (Zombie item : availableZombies) {
			sol = item.listMsg(new StringBuilder());
		}
		return sol;
	}

	// Dado el índice del array, dar el nombre del zombie
	// Llamado en game.computer()
	public static String zombieName(int pos){
		String sol = null;
		int j = 0;
		for (Zombie item : availableZombies) {
			if (pos == j){
				sol = item.getName();
			}
			++j;
		}
		return sol;
	}
	
	// Llamado para tener el rango del rand
	// en computer()
	public static int numZombies(){
		return availableZombies.length;

	}
}
