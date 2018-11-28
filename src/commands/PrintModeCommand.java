package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;
import play.Controller;
import printers.DebugPrinter;
import printers.BoardPrinter;
import printers.ReleasePrinter;

public class PrintModeCommand extends Command {
	private String mode;
	
	public PrintModeCommand() {
		super("printmode", "[P]rintMode <mode>", "change print mode [Release|Debug]");
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		BoardPrinter p = null;
		switch(mode) {
		case "release":
		case "r": p = new ReleasePrinter(game); break;
		case "debug":
		case "d": p = new DebugPrinter(game); break;
		default: throw new CommandExecuteException("Unknown print mode: " + mode); // Esto no debería ser de parse?
		}
		return false;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(!commandWords[0].equals(this.commandText) && !commandWords[0].equals(this.commandText.substring(0, 1)))
			return null;
		
		if (commandWords.length != 2)
			throw new CommandParseException("Incorrect number of arguments for " + this.commandText + " command: " + this.commandTextMsg);

		this.mode = commandWords[1];
		return this;
	}
}
