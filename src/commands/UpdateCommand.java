package commands;

import logic.Game;

// No extends de NoParamsCommand porque el parse tiene que ser distinto:
// No puede preguntar por la primera letra
public class UpdateCommand extends Command {
	
	// none???
	public UpdateCommand() {
		super("", "[none]", "Skips cycle.");
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}
	
	public Command parse(String[] commandWords) {
		if(commandWords[0].equals(this.commandText))
			return this;

		else return null;		
	}
}
