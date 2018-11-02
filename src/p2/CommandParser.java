package p2;

// Clase utilidad: todos sus métodos son estáticos
public class CommandParser {
	
	private static Command[] availableCommands = {
		new AddCommand(null, 0, 0),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new ListCommand(),
		new UpdateCommand(),
	};
	
	static public Command parseCommand(String[] words, Controller controller) {
		Command com = null;
		
		for (Command item : availableCommands) {
			com = item.parse(words, controller);
		}
		
		// Si es null no se va a pintar el tablero
		return com;
	}
}
