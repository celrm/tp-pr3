
package commands;

import logic.Game;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;

public class SaveCommand extends Command {
	private String fileName;
	public SaveCommand() {
		super("save", "save <fileName>", "saves the actual state of the game in fileName");
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		boolean primeraletra = commandWords[0].equals(this.commandText.substring(0, 1));
		if (!commandWords[0].equals(this.commandText) &&  !primeraletra ){
			return null;
		}
		if (commandWords.length != 2){
			throw new CommandParseException("Incorrect number of arguments for " + this.commandText + " command: " + this.commandTextMsg);
		}
		fileName = commandWords[1];
		
	}

}