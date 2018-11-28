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
		boolean executed = game.addPlantToGame(plant, x, y);
		if(executed)
			game.update();
		return executed;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException, NumberFormatException {
		boolean primeraletra = commandWords[0].equals(this.commandText.substring(0, 1));
		if(!commandWords[0].equals(this.commandText) && !primeraletra)
			return null;
		
		// Aquí va a sacar wrong command también, pero al fin y al cabo lo es
		if (commandWords.length != 4)
			throw new CommandParseException("Wrong number of parameters: " + commandWords.length);

		int x = Integer.parseInt(commandWords[2]);
		int y = Integer.parseInt(commandWords[3]);

		// El -1 para el glitch
		if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY-1)
			throw new CommandParseException("Wrong position: (" +x+","+y+") is outside the field");

		this.plant = PlantFactory.getPlant(commandWords[1]); // Esto antes estaba en execute pero en clase dijo que no
		this.x = x;
		this.y = y;
		return this;
	}
}
