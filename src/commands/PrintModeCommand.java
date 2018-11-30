package commands;

import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import logic.Game;
import printers.BoardPrinter;
import printers.PrinterManager;


public class PrintModeCommand extends Command {
	private String mode;
	
	public PrintModeCommand() {
		super("printmode", "[P]rintMode <mode>", "change print mode [Release|Debug]");
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		BoardPrinter p = PrinterManager.parsePrinter(mode);

		game.setPrinter(p);
		return true;
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
