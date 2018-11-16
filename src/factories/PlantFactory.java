package factories;

import objects.Plant;
import objects.Nuez;
import objects.Peashooter;
import objects.Petacereza;
import objects.Sunflower;

public class PlantFactory {
		private static Plant[] availablePlants = {
			new Sunflower(),
			new Peashooter(),
			new Petacereza(),
			new Nuez()
		};
		// TODO la he liado con el clone		
		public static Plant getPlant(String plantName){
			Plant p = null;
			switch(plantName) {
			case "sunflower":
			case "s": p = new Sunflower(); break;
			case "peashooter":
			case "p": p = new Peashooter(); break;
			case "petacereza":
			case "c": p = new Petacereza(); break;
			case "nuez": 
			case "n": p = new Nuez(); break;				
			}
			return p;
		}
		
		public static String listOfAvailablePlants() {
			StringBuilder sol = new StringBuilder();
			for (Plant item : availablePlants) {
				sol.append(item.getNameMsg()).append(":");
				sol.append(" Cost: ").append(Integer.toString(item.getCost())).append(" suncoins");
				sol.append(" Harm: ").append(Integer.toString(item.getHarm())).append("\n");
			}
			return sol.toString();
		}
}
