package printers;

import logic.Game;

public class ReleasePrinter extends BoardPrinter {

	
	public ReleasePrinter(Game game) {
		encodeGame(game);
		boardX = Game.DIMX;
		boardY = Game.DIMY;
	}

	@Override
	public void encodeGame(Game game) {
	 	board = new String[boardX][boardY];
		for(int i = 0; i < boardX; i++) {
			for(int j = 0; j < boardY; j++) {
				board[i][j] = space;
			}		
		}
			
		for(int i = 0; i < boardX; i++) {
			for(int j = 0; j < boardY; j++) {				
				String s = game.toString(i,j);
				if(!s.equals("")) 
					board[i][j] = s;
			}		
		}
	}
	
	
	public String cabezera(Game game) {
		StringBuilder str = new StringBuilder();
		str.append("Number of cycles: ").append(Integer.toString(game.getCiclos()));
		str.append("\nSun coins: ").append(Integer.toString(game.getSoles()));
		str.append("\nRemaining Zombies: ").append(game.remZombies());
		str.append("\n");
		return str.toString();
	}

}
