package commands;

import exceptions.CommandParseException;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandText, String commandTextMsg, String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
	}
	
	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(!word(commandWords[0].toLowerCase(),1))
			return null;
		
		if (commandWords.length != 1)
			throw new CommandParseException(this.commandText + " command has no arguments");
		return this;
	}

}
