package p2;

public class AddCommand extends Command {
	
	public AddCommand() {
		super("add", "[A]dd", "add flower");
	}

	@Override
	public void execute(Game game, Controller controller) {
		

	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		if (commandWords.length != 4) System.out.println("Wrong parameters.");
		else {
			int x = Integer.parseInt(commandWords[2]);
			int y = Integer.parseInt(commandWords[3]);
			
			if(x<0 || y<0 || x>=Game.DIMX || y>=Game.DIMY) System.out.println("Wrong position.");
			
			else {
				Command com = new AddCommand(commandWords[1], x, y);
				return com;
			}
		}
		//TODO el error
		return new AddCommand();
	}

}
