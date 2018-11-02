package commands;

import logic.Game;
import p2.Controller;

public class ResetCommand extends NoParamsCommand{

	public ResetCommand() {
		super("reset", "[R]eset", "Starts a new game.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		game.reset();
	}
}
