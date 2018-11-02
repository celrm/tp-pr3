package p2;

public abstract class NoParamsCommand extends Command {

	public NoParamsCommand(String commandText, String commandTextMsg,
			String helpTextMsg) {
		super(commandText, commandTextMsg, helpTextMsg);
		// TODO Auto-generated constructor stub
	}
	
	public abstract void execute(Game game, Controller controller);
	
	@Override
	public Command parse(String[] commandWords, Controller controller) {
		return null;
		// TODO Auto-generated method stub
		
	}

}
