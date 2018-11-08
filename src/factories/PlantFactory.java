package factories;

import objects.Plant;
import objects.Nuez;
import objects.Peashooter;
import objects.Petacereza;
import objects.Sunflower;

public class PlantFactory {
		private static Plant[] availablePlants = {
			new Sunflower(0,0,null),
			new Peashooter(0, 0,null),
			new Petacereza(0, 0,null),
			new Nuez(0, 0,null)
		};
		
		public static Plant getPlant(String plantName,int x, int y){
			Plant p = null;
			for (Plant item : availablePlants) {
				if(plantName.equals(item.getName()))
					p = item;
			}
			p.setPosition(x, y);
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
