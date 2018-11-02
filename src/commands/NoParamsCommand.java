package commands;

import logic.Game;
import p2.Controller;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandText, String commandTextMsg,
			String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void execute(Game game, Controller controller);
	
	@Override
	public Command parse(String[] commandWords, Controller controller) {
		if(commandWords[0].equals(this.commandText))
			return this;
		
		else return null;		
	}

}
