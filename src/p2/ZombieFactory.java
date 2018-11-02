package p2;
// Dónde va addZombie?
// puedes crear una factoría y un nuevo comando addZombie
public class ZombieFactory {
		private static Zombie[] availableZombies = {
			
		};
		
		public static Zombie getZombie(String zombieName){
			Zombie z = null;
			for (Zombie item : availableZombies) {
				if(zombieName.equals(item.getName()))
					z = item;
			}
			return z;
		}
		
		public static String listOfAvilablePlants() {
			String sol = "";
			for (Zombie item : availableZombies) {
				sol += item.getName() + "\n";
			}
			return sol;
		}
}
