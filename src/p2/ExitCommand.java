package p2;

public class ExitCommand extends NoParamsCommand{

	public ExitCommand() {
		super("exit", "[E]xit", "exit game");
	}

	@Override
	public void execute(Game game, Controller controller) {
		// execute se implementa invocando algún método con el objeto game pasado como parámetro y ejecutando alguna acción más.
		
	}
}
