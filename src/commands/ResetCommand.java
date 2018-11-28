package commands;

import logic.Game;
import play.Controller;

public class ResetCommand extends NoParamsCommand{

	public ResetCommand() {
		super("reset", "[R]eset", "Starts a new game.");
	}

	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}
}
