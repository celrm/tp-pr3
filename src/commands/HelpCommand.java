package commands;

import logic.Game;

public class HelpCommand extends NoParamsCommand {

	public HelpCommand() {
		super("help", "[H]elp", "Prints this help message.");
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(CommandParser.commandHelp());
		return false;
	}
}