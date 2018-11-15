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
			for (Plant item : availablePlants) {
				if(plantName.equals(item.getName()) || plantName.equals(item.getSymbol()))
					p = item;
			}
			if (p != null)
			return (Plant) p.clone();
			else return null;
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
