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
		// TODO la he liado con el clone
		public static Zombie getZombie(String zombieName){
			Zombie z = null;
			switch(zombieName) {
			case "zombie comun":
			case "zco": z = new ZombieComun(); break;
			case "zombie deportista":
			case "zd": z = new ZombieDeportista(); break;
			case "zombie caracubo":
			case "zca": z = new ZombieCaracubo(); break;		
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

		public static int numZombies(){
			return availableZombies.length;

		}
}
