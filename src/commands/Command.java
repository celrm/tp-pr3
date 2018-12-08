package commands;

import utils.MyStringUtils;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import exceptions.FileContentsException;
import logic.Game;

public abstract class Command {

	protected final String commandText;
	protected String commandTextMsg;
	private String helpTextMsg;

	public Command(String commandText, String commandTextMsg, String helpTextMsg) {
		this.commandText =  commandText;
		this.commandTextMsg = commandTextMsg;
		this.helpTextMsg = helpTextMsg;
	}
	
	public abstract boolean execute(Game game) throws CommandExecuteException, FileContentsException;
	public abstract Command parse(String[] commandWords) throws CommandParseException;
	
	public String helpText() {
		return commandTextMsg + ": " + this.helpTextMsg;
	}
	
	protected boolean word(String str,int n) {
		boolean primeraletra = str.equals(this.commandText.substring(0, n));
		if(!str.equals(this.commandText) && !primeraletra)
			return false;
		return true;
	}
	
	protected void numParameters(int length, int n) throws CommandParseException {
		if (length != n)
			throw new CommandParseException("Incorrect number of arguments for " + MyStringUtils.capitalize(this.commandText) + " command: " + this.commandTextMsg);
	}
}