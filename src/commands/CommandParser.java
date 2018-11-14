package commands;

import play.Controller;

// Clase utilidad: todos sus métodos son estáticos
public class CommandParser {
	
	private static Command[] availableCommands = {
		new UpdateCommand(),
		new AddCommand(null, 0, 0),
		new HelpCommand(),
		new ExitCommand(),
		new ListCommand(),
		new PrintModeCommand(null),
		new ResetCommand(),
		new ZombieListCommand(),
	};
	
	public static Command parseCommand(String[] words, Controller controller) {
		Command com = null;
		for (Command item : availableCommands) {
			if(com==null)
				com = item.parse(words, controller);
		}
		
		// Si es null no se va a pintar el tablero
		return com;
	}
	
	// Lo usa HelpCommand
	public static String commandHelp() {
		StringBuilder strb = new StringBuilder();
		for (Command item : availableCommands) {
			strb.append(item.helpText() + "\n");
		}
		String str = strb.toString();		
		return str;
	}
}
