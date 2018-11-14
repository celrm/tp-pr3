package printers;

import logic.Game;

// TODO todo
public class DebugPrinter extends BoardPrinter implements GamePrinter{
	String[] board;
	final String space = " ";
	
	public DebugPrinter(Game game, int dimX, int dimY) {
		encodeGame(game);
	}

	@Override
	public void encodeGame(Game game) {
		
		board = new String[game.get_tot()];
		int i = 0;
		int x = 0;
		int y = 0;
		while (i < game.get_tot()){
			boolean found = false;
			while (y < Game.DIMY && !found ){
				String s = game.toStringDebug(x,y);
				if(!s.equals("")) {
					board[i] = s;
					++i;
				}
				else ++y;
			}
		}
		
	}
}
