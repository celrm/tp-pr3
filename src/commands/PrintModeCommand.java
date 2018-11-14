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
		case "r": p = new ReleasePrinter(game, Game.DIMX, Game.DIMY);
		case "debug":
		case "d": p = new DebugPrinter(game, Game.DIMX, Game.DIMY);
		}
		controller.setPrinter(p);
		controller.printGame();
		controller.setNoPrintGameState();
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		// TODO preguntar por la primera letra
		if(commandWords[0] != this.commandText || commandWords[0] != this.commandText.split("\\s+")[0])
			return null;
		
		// TODO aquí va a sacar wrong command también
		if (commandWords.length != 2) {
			System.out.println("Wrong parameters.");
			return null;
		}
		if(commandWords[1] != "debug" || commandWords[1] != "d" || commandWords[1] != "release" || commandWords[1] != "r"){
			System.out.print("Printmode doesn't exists");
			return null;
		}
		Command com = new PrintModeCommand(commandWords[1]);
		return com;
	}
}
