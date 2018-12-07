package commands;

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
	
//	Some commands may generate an error in the execute or parse methods.
//	In the absence of exceptions, they must the tell the controller not to print the board
	
	public abstract boolean execute(Game game) throws CommandExecuteException, FileContentsException;
	
	public abstract Command parse(String[] commandWords) throws CommandParseException, NumberFormatException;

	public String helpText() {
		return commandTextMsg + ": " + this.helpTextMsg;
	}
	public boolean word(String str,int n) {
		boolean primeraletra = str.equals(this.commandText.substring(0, n));
		if(!str.toLowerCase().equals(this.commandText) && !primeraletra)
			return false;
		return true;
	}
}