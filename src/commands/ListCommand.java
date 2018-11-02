package commands;

import logic.Game;
import p2.Controller;
import factories.PlantFactory;

public class ListCommand extends NoParamsCommand{

	public ListCommand() {
		super("list", "[L]ist", "Prints the list of available plants.");
	}

	@Override
	public void execute(Game game, Controller controller) {		
		System.out.println(PlantFactory.listOfAvilablePlants());
		controller.setNoPrintGameState();
	}
}
