package printers;

import logic.Game;

public class ReleasePrinter extends BoardPrinter {
	private String symbol;
	private String name;
	
	public ReleasePrinter() {
		super("r","release", 10);
	}

	@Override
	public void encodeGame(Game game) {
		boardX = Game.DIMX;
		boardY = Game.DIMY;
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
	
	
	public String cabecera(Game game) {
		StringBuilder str = new StringBuilder();
		str.append("Current cycle: ").append(Integer.toString(game.getCiclos()));
		str.append("\nSun coins: ").append(Integer.toString(game.getSoles()));
		str.append("\nRemaining Zombies: ").append(game.remZombies());
		str.append("\n");
		return str.toString();
	}
	
	public ReleasePrinter parse(String mode) {
		if(!mode.equals(this.symbol) && !mode.equals(this.name))
			return null;

		return this;
	}

}
