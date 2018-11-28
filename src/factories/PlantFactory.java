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
		
	public static Plant getPlant(String plantName) throws CommandParseException {
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
		default : throw new CommandParseException("No existe esta planta: " + plantName);
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
