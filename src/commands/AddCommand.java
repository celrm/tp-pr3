package commands;

import logic.Game;
import objects.Plant;
import utils.MyStringUtils;
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
		try {
			game.addPlantToGame(plant, x, y);
		} catch(CommandExecuteException ex) {
			throw new CommandExecuteException("Failed to add " + MyStringUtils.capitalize(plant.getName()) + ": " + ex.getMessage());
		}
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) throws CommandParseException {
		if(!word(commandWords[0].toLowerCase(),1))
			return null;
		
		numParameters(commandWords.length, 4);
		
		this.plant = PlantFactory.getPlant(commandWords[1].toLowerCase());
		if (plant == null)
			throw new CommandParseException("Unknown plant name: " + commandWords[1]);
			
		try {
			this.x = Integer.parseInt(commandWords[2]);
			this.y = Integer.parseInt(commandWords[3]);
		}
		catch (NumberFormatException ex) {
			throw new CommandParseException("Invalid argument for " + this.commandText + " command, number expected: " + this.commandTextMsg);
		}
		return this;
	}
}
