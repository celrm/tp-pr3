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
//	The available commands are:
//		[A]dd <plant> <x> <y>: Add a plant in position (x, y).
//		[H]elp: Show this help message.
//		[R]eset: Start a new game.
//		[E]xit: Terminate the game.
//		[L]ist: Show the list of available plants.
//		[N]one: Update the game without any user action.
//		[P]rintMode <mode>: Change the print mode [Release|Debug].
//		[Z]ombielist: Show the available Zombies.
//		addzombie <zombie> <x> <y>: Add a zombie in position (x,y)
}