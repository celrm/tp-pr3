package printers;

import logic.Game;

public class ReleasePrinter extends BoardPrinter implements GamePrinter
{
	String[][] board;
	final String space = " ";
	
	public ReleasePrinter(Game game, int dimX, int dimY) {
		encodeGame(game);
	}

	@Override
	public void encodeGame(Game game) {
	 	board = new String[Game.DIMX][Game.DIMY];
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
		}
	}
}
