package commands;

import logic.Game;
import play.Controller;
import objects.Plant;
import factories.PlantFactory;

public class PrintModeCommand extends Command {
	private Mode mode;

	public PrintModeCommand(Mode mode) {
		super("printmode", "[P]rintMode <mode>", "change print mode [Release|Debug]");
		this.mode = mode;
	}
	
	@Override
	public void execute(Game game, Controller controller) {
		
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

		switch(commandWords[1]) {
		case 
		}

		Command com = new PrintModeCommand(mode);
		return com;
	}
}
