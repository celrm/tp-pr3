package commands;

import logic.Game;
import play.Controller;
import factories.PlantFactory;

public class ZombieListCommand extends NoParamsCommand{

	public ZombieListCommand() {
		super("list", "[L]ist", "Prints the list of available zombies.");
	}

	@Override
	public void execute(Game game, Controller controller) {		
		System.out.println(ZombieFactory.listOfAvilableZombies());
		controller.setNoPrintGameState();
	}
}
