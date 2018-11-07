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
			String sol = "";
			for (Plant item : availablePlants) {
				sol += item.getName() + ": Cost: " + Integer.toString(item.getCost()) + " suncoins";
				sol += " Harm: " + Integer.toString(item.getHarm()) + "\n";
			}
			return sol;
		}
}
