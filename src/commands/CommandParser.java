package commands;

import exceptions.CommandParseException;

// Clase utilidad: todos sus métodos son estáticos
public class CommandParser {
	
	private static Command[] availableCommands = {
		new UpdateCommand(),
		new AddCommand(),
		new HelpCommand(),
		new ExitCommand(),
		new ListCommand(),
		new PrintModeCommand(),
		new ResetCommand(),
		new ZombieListCommand(),
		new SaveCommand(),
		new LoadCommand()
	};
	
	public static Command parseCommand(String[] words) throws CommandParseException {
		Command com = null;
		for (Command item : availableCommands) {
			if(com==null)
				com = item.parse(words);
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
