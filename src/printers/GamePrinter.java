package printers;

import logic.Game;

public interface GamePrinter {

	abstract void encodeGame(Game game);

	abstract public String printGame(Game game);
}