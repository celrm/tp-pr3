package commands;

import logic.Game;
import play.Controller;
import objects.Plant;
import factories.PlantFactory;

public class AddCommand extends Command {
	private String plantName;
	private int x;
	private int y;

	public AddCommand(String plant, int x, int y) {
		super("add", "[A]dd <plant> <x> <y>", "Adds a plant in position x, y.");
		this.plantName = plant;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void execute(Game game, Controller controller) {
		Plant plant = PlantFactory.getPlant(plantName);
		
		boolean executed = false;
		if (plant != null){
			executed = game.addPlantToGame(plant, x, y);
		}
		else System.out.println("Plant doesn't exist");
		if(!executed)
			controller.setNoPrintGameState();
		else{
			game.update();
		}
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		boolean primeraletra = commandWords[0].equals(this.commandText.substring(0, 1));
		if(!commandWords[0].equals(this.commandText) && !primeraletra)
			return null;
		
		// Aquí va a sacar wrong command también, pero al fin y al cabo lo es
		if (commandWords.length != 4) {
			System.out.println("Wrong parameters.");
			return null;
		}

		int x = Integer.parseInt(commandWords[2]);
		int y = Integer.parseInt(commandWords[3]);

		// El -1 para el glitch
		if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY-1) {
			System.out.println("Wrong position.");
			return null;
		}

		Command com = new AddCommand(commandWords[1], x, y);
		return com;
	}
}
