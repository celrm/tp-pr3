package p2;

public class AddCommand extends Command {
	private String commandText = "add";
	private String commandTextMsg = "[A]dd";
	private String helpTextMsg = "add flower";
	
	public AddCommand() {
		super(commandText, commandTextMsg, helpTextMsg);
	}

	@Override
	void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	void parse() {
		// TODO Auto-generated method stub

	}

}
