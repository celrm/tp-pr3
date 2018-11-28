package commands;

import logic.Game;
import factories.PlantFactory;

public class ListCommand extends NoParamsCommand{

	public ListCommand() {
		super("list", "[L]ist", "Prints the list of available plants.");
	}

	@Override
	public boolean execute(Game game) {		
		System.out.println(PlantFactory.listOfAvailablePlants());
		return false;
	}
}
