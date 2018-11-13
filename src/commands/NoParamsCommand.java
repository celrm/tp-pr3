package commands;

import logic.Game;
import play.Controller;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandText, String commandTextMsg,
			String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	public abstract void execute(Game game, Controller controller);
	
	@Override
	public Command parse(String[] commandWords, Controller controller) {
		boolean primeraletra = commandWords[0].equals(this.commandText.substring(0, 1));
		if(commandWords[0].equals(this.commandText) || primeraletra)
			return this;
		
		else return null;		
	}

}
