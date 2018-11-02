package commands;

import logic.Game;
import p2.Controller;

public class ExitCommand extends NoParamsCommand{

	public ExitCommand() {
		super("exit", "[E]xit", "Terminates the program.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		// execute se implementa invocando algún método con el objeto game pasado como parámetro y ejecutando alguna acción más.
		
	}
}
