package commands;

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
	public void execute(Game game, Controller controller) {
		BoardPrinter p = null;
		boolean success = true;
		switch(mode) {
		case "release":
		case "r": p = new ReleasePrinter(game); break;
		case "debug":
		case "d": p = new DebugPrinter(game); break;
		default: {
			System.out.print("Printmode doesn't exist");
			success = false;
			}
		}
		if(success) {
			controller.setPrinter(p);
			controller.printGame();
		}
		controller.setNoPrintGameState();
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		if(!commandWords[0].equals(this.commandText) && !commandWords[0].equals(this.commandText.substring(0, 1)))
			return null;
		
		// Aquí va a sacar wrong command también, pero al fin y al cabo lo es
		if (commandWords.length != 2) {
			System.out.println("Wrong parameters.");
			return null;
		}

		this.mode = commandWords[1];
		return this;
	}
}
