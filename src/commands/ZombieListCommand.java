package commands;

import logic.Game;
import play.Controller;
import factories.ZombieFactory;

public class ZombieListCommand extends NoParamsCommand{

	public ZombieListCommand() {
		super("zombielist", "[Z]ombieList", "Prints the list of available zombies.");
	}

	@Override
	public void execute(Game game, Controller controller) {		
		System.out.println(ZombieFactory.listOfAvailableZombies());
		controller.setNoPrintGameState();
	}
}
