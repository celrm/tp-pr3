package factories;

import objects.Zombie;
import objects.ZombieCaracubo;
import objects.ZombieComun;
import objects.ZombieDeportista;

public class ZombieFactory {
		private static Zombie[] availableZombies = {
			new ZombieComun(null, 0, 0, 0, 0, 0),
			new ZombieDeportista(null, 0, 0, 0, 0, 0),
			new ZombieCaracubo(null, 0, 0, 0, 0, 0)
		};
		
		public static Zombie getZombie(String zombieName){
			Zombie z = null;
			for (Zombie item : availableZombies) {
				if(zombieName.equals(item.getName()))
					z = item;
			}
			return z;
		}
		
		public static String listOfAvailableZombies() {
			String sol = "";
			for (Zombie item : availableZombies) {
				sol += item.getName() + ": Speed: " + Integer.toString(item.getSpeed());
				sol += " Harm: " + Integer.toString(item.getHarm()); 
				sol += " Life: " + Integer.toString(item.getVida()) + "\n";
			}
			return sol;
		}
		
		
}
