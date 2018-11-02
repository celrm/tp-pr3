package p2;

public class ListCommand extends Command{

	public ListCommand(String commandText, String commandTextMsg,
			String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Game game, Controller controller) {
		// TODO Auto-generated method stub
		// Usar PlantFactory.listOfAvailablePlants
		
	}

	@Override
	public Command parse(String[] commandWords, Controller controller) {
		// TODO Auto-generated method stub
		
	}
	

}
