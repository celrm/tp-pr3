package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;
import play.Controller;

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
	
	public abstract boolean execute(Game game) throws CommandExecuteException;
	
	public abstract Command parse(String[] commandWords) throws CommandParseException, NumberFormatException;

	public String helpText() {
		return commandTextMsg + ": " + this.helpTextMsg;
	}
}