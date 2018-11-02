package p2;

public abstract class Command {
	protected final String commandText;
	private String commandTextMsg;
	private String helpTextMsg;

	public Command(String commandText, String commandTextMsg, String helpTextMsg) {
		this.commandText =  commandText;
		this.commandTextMsg = commandTextMsg;
		this.helpTextMsg = helpTextMsg;
	}
	
//	Some commands may generate an error in the execute or parse methods.
//	In the absence of exceptions , they must the tell the controller not to print the board
	

	public abstract void execute(Game game, Controller controller);
	
	public abstract Command parse(String[] commandWords, Controller
	controller);

	public String helpText() {
		return " " + commandText + ": " + this.helpTextMsg;
	}
}