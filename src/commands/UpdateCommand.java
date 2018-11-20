package commands;

import logic.Game;
import play.Controller;

// No extends de NoParamsCommand porque el parse tiene que ser distinto:
// No puede preguntar por la primera letra
public class UpdateCommand extends Command {

	public UpdateCommand() {
		super("", "[none]", "Skips cycle.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		game.update();
	}
	
	public Command parse(String[] commandWords, Controller controller) {
		if(commandWords[0].equals(this.commandText)) {
			return this;
		}
		else return null;		
	}
}
