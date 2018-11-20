package commands;

import play.Controller;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandText, String commandTextMsg,
			String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	@Override
	public Command parse(String[] commandWords, Controller controller) {
		if(commandWords[0].equals(this.commandText) || commandWords[0].equals(this.commandText.substring(0, 1))) {
			return this;
		}
		else return null;		
	}

}
