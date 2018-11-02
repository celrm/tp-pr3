package p2;

public abstract class Command {
	private String commandText;
	private String commandTextMsg;
	private String helpTextMsg;

	public Command(String commandText, String commandTextMsg, String helpTextMsg) {
		this.commandText =  commandText;
		this.commandTextMsg = commandTextMsg;
		this.helpTextMsg = helpTextMsg;
	}

	abstract void execute();
	
	abstract void parse();

	public String helpText() {
		return this.helpTextMsg;
	}
}
