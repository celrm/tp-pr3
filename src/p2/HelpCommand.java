package p2;

public class HelpCommand extends NoParamsCommand {

	public HelpCommand() {
		super("help", "[H]elp", "show help");
	}

	@Override
	public void execute(Game game, Controller controller) {
		// execute se implementa invocando algún método con el objeto game pasado como parámetro y ejecutando alguna acción más.
		
	}
}
