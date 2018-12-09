package factories;

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

	public static Zombie getZombie(String zombieName) {
		Zombie z = null;
		for (Zombie item : availableZombies) {
			if(z==null)
				z = item.parse(zombieName);
		}
		return z;
	}
	
	// ZombieListCommand.execute()
	public static String listOfAvailableZombies() {
		StringBuilder sol = new StringBuilder();
		for (Zombie item : availableZombies) {
			sol.append(item.listMsg(new StringBuilder())+'\n');
		}
		return sol.toString();
	}

	// Dado el índice del array, dar el nombre del zombie
	// Game.computer()
	public static String zombieName(int pos){
		String sol = null;
		int j = 0;
		for (Zombie item : availableZombies) {
			if (pos == j){
				sol = item.getIdent();
			}
			++j;
		}
		return sol;
	}
	
	// Para tener el rango del rand
	// Game.computer()
	public static int numAvZombies(){
		return availableZombies.length;

	}
}
