package commands;

import logic.Game;
import factories.ZombieFactory;

public class ZombieListCommand extends NoParamsCommand{

	public ZombieListCommand() {
		super("zombielist", "[Z]ombieList", "Prints the list of available zombies.");
	}

	@Override
	public boolean execute(Game game) {		
		System.out.println(ZombieFactory.listOfAvailableZombies());
		return false;
	}
}
