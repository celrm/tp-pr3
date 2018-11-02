package commands;

import p2.Controller;

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
	
	public static Command parseCommand(String[] words, Controller controller) {
		Command com = null;
		
		for (Command item : availableCommands) {
			com = item.parse(words, controller);
		}
		
		// Si es null no se va a pintar el tablero
		return com;
	}
	
	// Lo usa HelpCommand
	public static String commandHelp() {
		String str = null;
		// TODO StringBuilder?
		
		for (Command item : availableCommands) {
			str += item.helpText() + "\n";
		}
		
		// Si es null no se va a pintar el tablero
		return str;
	}
}
