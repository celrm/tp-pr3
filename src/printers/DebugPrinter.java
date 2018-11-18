 package printers;

import logic.Game;

public class DebugPrinter extends BoardPrinter{
	
	public DebugPrinter(Game game) {
		boardX = 1;
		boardY = game.get_tot() + 1;
		cellsize = 25;
	}

	@Override
	public void encodeGame(Game game) {
		boardY = game.get_tot() + 1;
		board = new String[boardX][boardY];
		int i = 0;
		while( i < game.numPlantas()){
			board[0][i] = game.toStringDebugp(i);
			++i;
		}
		board[0][i] = "--";
		++i;
		while (i< boardY){
			board[0][i] = game.toStringDebugz(i);
			++i;
		}
	}

	public String cabecera(Game game) {
		StringBuilder str = new StringBuilder();
		str.append("Number of cycles: ").append(Integer.toString(game.getCiclos()));
		str.append("\nSun coins: ").append(Integer.toString(game.getSoles()));
		str.append("\nRemaining Zombies: ").append(game.remZombies());
		str.append("\nLevel: ").append(game.getLevelName());
		str.append("\nSeed: ").append(game.seed());
		str.append("\n");
		return str.toString();
	}

}
