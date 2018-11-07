package factories;

import objects.Plant;
import objects.Nuez;
import objects.Peashooter;
import objects.Petacereza;
import objects.Sunflower;

public class PlantFactory {
		private static Plant[] availablePlants = {
			new Sunflower(null, 0, 0, 0, 0, 0),
			new Peashooter(null, 0, 0, 0, 0, 0),
			new Petacereza(null, 0, 0, 0, 0, 0),
			new Nuez(null, 0, 0, 0, 0, 0)
		};
		
		public static Plant getPlant(String plantName){
			Plant p = null;
			for (Plant item : availablePlants) {
				if(plantName.equals(item.getName()))
					p = item;
			}
			return p;
		}
		
		public static String listOfAvailablePlants() {
			StringBuilder sol = new StringBuilder();
			for (Plant item : availablePlants) {
				sol.append(item.getName()).append(":");
				sol.append(" Cost: ").append(Integer.toString(item.getCost())).append(" suncoins");
				sol.append(" Harm: ").append(Integer.toString(item.getHarm())).append("\n");
			}
			return sol.toString();
		}
}
