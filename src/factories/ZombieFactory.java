package factories;

import exceptions.CommandParseException;
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

	public static Zombie getZombie(String zombieName){
		Zombie z = null;
		switch(zombieName) {
		case "zombie comun": z = new ZombieComun(); break;
		case "zombie deportista": z = new ZombieDeportista(); break;
		case "zombie caracubo": z = new ZombieCaracubo(); break;		
		//default : throw new Exception("No existe este zombie: " + zombieName); ????
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
