package commands;

import logic.Game;
import play.Controller;

public class UpdateCommand extends NoParamsCommand {

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
