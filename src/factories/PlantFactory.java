package factories;

import exceptions.CommandParseException;
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
		
	public static Plant getPlant(String plantName) {
		Plant p = null;
		for (Plant item : availablePlants) {
			if(p==null)
				p = item.parse(plantName);
		}
		return p;
	}

	public static String listOfAvailablePlants() {
		String sol = null;
		for (Plant item : availablePlants) {
			sol = item.listMsg(new StringBuilder());
		}
		return sol;
	}
}
