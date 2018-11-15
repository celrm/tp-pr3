package factories;

import objects.Zombie;
import objects.ZombieCaracubo;
import objects.ZombieComun;
import objects.ZombieDeportista;

public class ZombieFactory {
		private static Zombie[] availableZombies = {
			new ZombieComun(0, 0, null),
			new ZombieDeportista(0, 0, null),
			new ZombieCaracubo(0, 0, null)
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
		
		//una puta mierda de funcion, como la de arriba pero no se me ocurria otra manera 
		@SuppressWarnings("unused")
		public static int numZombies(){
			return availableZombies.length;

		}
}
