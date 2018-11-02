package commands;

import logic.Game;
import p2.Controller;

public class ExitCommand extends NoParamsCommand{

	public ExitCommand() {
		super("exit", "[E]xit", "Terminates the program.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		// TODO quién coño hace esto, game o controller
		controller.exit();
	}
}
