package commands;

import logic.Game;

public class ExitCommand extends NoParamsCommand{

	public ExitCommand() {
		super("exit", "[E]xit", "Terminates the program.");
	}

	@Override
	public boolean execute(Game game) {
		game.exit();
		return false;
	}
}
