package factories;

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
		}
		return z;
	}
		
	public static String listOfAvailableZombies() {
		StringBuilder sol = new StringBuilder();
		for (Zombie item : availableZombies) {
			sol.append(item.getNameMsg()).append(":");
			sol.append(" Speed: ").append(Integer.toString(item.getSpeed()));
			sol.append(" Harm: ").append(Integer.toString(item.getHarm())); 
			sol.append(" Life: ").append(Integer.toString(item.getVida())).append("\n");
		}
		return sol.toString();
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
