package p2;

public class ListCommand extends NoParamsCommand{

	public ListCommand() {
		super("list", "[L]", "show list");
	}

	@Override
	public void execute(Game game, Controller controller) {
		// Usar PlantFactory.listOfAvailablePlants
		// execute se implementa invocando algún método con el objeto game pasado como parámetro y ejecutando alguna acción más.
		
	}
}
