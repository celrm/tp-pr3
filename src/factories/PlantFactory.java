package factories;

import objects.Plant;

public class PlantFactory {
		private static Plant[] availablePlants = {
			new Sunflower();
			new Peashooter();
			new Petacereza();
			new Nuez();
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
