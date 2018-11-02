package commands;

import logic.Game;
import p2.Controller;

public class UpdateCommand extends NoParamsCommand {

	public UpdateCommand() {
		super("", "[none]", "Skips cycle.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		// TODO Auto-generated method stub
		// execute se implementa invocando algún método con el objeto game pasado como parámetro y ejecutando alguna acción más.
		
	}
}
