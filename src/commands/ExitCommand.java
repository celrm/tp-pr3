package commands;

import logic.Game;
import play.Controller;

public class ExitCommand extends NoParamsCommand{

	public ExitCommand() {
		super("exit", "[E]xit", "Terminates the program.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		// TODO quién coño hace esto, game o controller <-Controller ya que tiene que ver con run y es ajeno a logica juego??
		controller.exit();
	}
}
