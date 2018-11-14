package commands;

import logic.Game;
import play.Controller;

public class ExitCommand extends NoParamsCommand{

	public ExitCommand() {
		super("exit", "[E]xit", "Terminates the program.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		controller.exit();
	}
}
