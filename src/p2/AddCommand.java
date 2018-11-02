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
//		if(this.game.hayCosas(x,y)) System.out.println("There's already something there.");
//		else switch(plant) {
//			case "sunflower":
//			case "s": {
//				if (this.soles.num() >= Sunflower.COSTE) {
//					this.sunflowerList.add(x, y, this);
//					this.soles.add(-Sunflower.COSTE);
//				}
//				else System.out.println("Not enough cash.");
//			} break;
//			case "peashooter":
//			case "p": {
//				if (this.soles.num() >= Peashooter.COSTE) {
//					this.peashooterList.add(x, y, this);
//					this.soles.add(-Peashooter.COSTE);
//					sol = true;
//				}
//				else System.out.println("Not enough cash.");
//			} break;
//			default: System.out.println("Wrong plant."); break;
//			}
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
