package commands;

import logic.Game;
import p2.Controller;

public class UpdateCommand extends NoParamsCommand {

	public UpdateCommand() {
		super("", "[none]", "Skips cycle.");
	}

	@Override
	public void execute(Game game, Controller controller) {
		game.update();
	}
}
