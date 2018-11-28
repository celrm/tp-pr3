package commands;

import logic.Game;
import play.Controller;
import objects.Plant;
import exceptions.CommandExecuteException;
import exceptions.CommandParseException;
import factories.PlantFactory;

public class AddCommand extends Command {
	private Plant plant;
	private int x;
	private int y;

	public AddCommand() {
		super("add", "[A]dd <plant> <x> <y>", "Adds a plant in position x, y.");
	}
	
	@Override
	public boolean execute(Game game) throws CommandExecuteException {		
		game.addPlantToGame(plant, x, y);
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		boolean primeraletra = commandWords[0].equals(this.commandText.substring(0, 1));
		if(!commandWords[0].equals(this.commandText) && !primeraletra)
			return null;
		
		if (commandWords.length != 4)
			throw new CommandParseException("Incorrect number of arguments for " + this.commandText + " command: " + this.commandTextMsg);

		this.plant = PlantFactory.getPlant(commandWords[1]); // Esto antes estaba en execute pero en clase dijo que no
		
		try {
			int x = Integer.parseInt(commandWords[2]);
			int y = Integer.parseInt(commandWords[3]);
			
			// El -1 para el glitch
			if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY-1) // es este de execute??
				throw new CommandParseException("Failed to add " + plant.getName() + ": (" +x+", "+y+") is an invalid position");

			this.x = x;
			this.y = y;
			return this;
		}
		catch (NumberFormatException ex) {
			throw new CommandParseException("Invalid argument for " + this.commandText + " command, number expected: " + this.commandTextMsg);
		}
	}
}
