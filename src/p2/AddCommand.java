package p2;

public class AddCommand extends Command {
	private String plantName;
	private int x;
	private int y;

	public AddCommand(String plant, int x, int y) {
		super("add", "[A]dd", "add flower");
		this.plantName = plant;
		this.x = x;
		this.y = y;
	}

	@Override
	public void execute(Game game, Controller controller) {
		Plant plant = PlantFactory.getPlant(plantName);
		game.addPlantToGame(plant, x, y);
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		if(commandWords[0] != this.commandText)
			return null;

		if (commandWords.length != 4) {
			System.out.println("Wrong parameters.");
			return null;
		}

		int x = Integer.parseInt(commandWords[2]);
		int y = Integer.parseInt(commandWords[3]);

		if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY) {
			System.out.println("Wrong position.");
			return null;
		}

		Command com = new AddCommand(commandWords[1], x, y);
		return com;
	}
}
