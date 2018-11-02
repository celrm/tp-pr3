package commands;

import logic.Game;
import p2.Controller;

public class HelpCommand extends NoParamsCommand {

	public HelpCommand() {
		super("help", "[H]elp", "Prints this help message.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		System.out.println(CommandParser.commandHelp());
		controller.setNoPrintGameState();
	}
}