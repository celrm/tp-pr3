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
		for (int i = 0; i < game.get_tot(); ++i){
			board[i] = space;
		}
	 	/*board = new String[Game.DIMX][Game.DIMY];
		for(int i = 0; i < Game.DIMX; i++) {
			for(int j = 0; j < Game.DIMY; j++) {
				board[i][j] = space;
			}		
		}
			
		for(int i = 0; i < Game.DIMX; i++) {
			for(int j = 0; j < Game.DIMY; j++) {
				String s = game.toString(i,j);
				if(!s.equals("")) 
					board[i][j] = s;
			}		
		}*/
	}
}
