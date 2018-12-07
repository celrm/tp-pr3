package commands;

import exceptions.CommandParseException;

public class CommandParser {
	
	// Muy importante mantener UpdateCommand el primero, por no acceder a "".
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
		return com;
	}
	
	// HelpCommand.execute()
	public static String commandHelp() {
		StringBuilder strb = new StringBuilder();
		
		for (Command item : availableCommands)
			strb.append(item.helpText() + "\n");
		
		return strb.toString();
	}
}
