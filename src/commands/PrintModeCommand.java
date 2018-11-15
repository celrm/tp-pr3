package commands;

import logic.Game;
import play.Controller;
import printers.DebugPrinter;
import printers.BoardPrinter;
import printers.ReleasePrinter;

public class PrintModeCommand extends Command {
	private String mode;
	
	public PrintModeCommand(String mode) {
		super("printmode", "[P]rintMode <mode>", "change print mode [Release|Debug]");
		this.mode = mode;
	}
	
	// Mejorar con lo de la herencia y eso
	@Override
	public void execute(Game game, Controller controller) {
		BoardPrinter p = null;
		switch(mode) {
		case "release":
		case "r": p = new ReleasePrinter(game);
		case "debug":
		case "d": p = new DebugPrinter(game);
		}
		controller.setPrinter(p);
		controller.printGame();
		controller.setNoPrintGameState();
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		// TODO preguntar por la primera letra
		if(!commandWords[0].equals(this.commandText) && !commandWords[0].equals(this.commandText.substring(0, 1)))
			return null;
		
		// TODO aquí va a sacar wrong command también
		if (commandWords.length != 2) {
			System.out.println("Wrong parameters.");
			return null;
		}
		boolean debug = commandWords[1].equals("debug");
		boolean d = commandWords[1].equals("d");
		boolean release = commandWords[1].equals("release");
		boolean r = commandWords[1].equals("r");		
		if(!debug && !d && !release && !r){
			System.out.print("Printmode doesn't exist");
			return null;
		}
		Command com = new PrintModeCommand(commandWords[1]);
		return com;
	}
}
