package printers;

import logic.Game;

public class DebugPrinter extends BoardPrinter{
	
	public DebugPrinter(Game game) {
		boardX = 1;
		boardY = game.get_tot();
		cellsize = 25;
	}

	@Override
	public void encodeGame(Game game) {
		board = new String[boardX][boardY];
		int index = 0;
		int x = 0;
		int y = 0;
		
		while (index < boardY){
			boolean found = false;
			while (y < Game.DIMY && !found ){
				String s = game.toStringDebug(x,y);
				if(!s.equals("")) {
					found = true;
					board[0][index] = s;
					++index;
				}
				++y;
			}
			if (!found){
				y = 0;
				++x;
			}
		}
		
	}

	public String cabecera(Game game) {
		StringBuilder str = new StringBuilder();
		str.append("Number of cycles: ").append(Integer.toString(game.getCiclos()));
		str.append("\nSun coins: ").append(Integer.toString(game.getSoles()));
		str.append("\nRemaining Zombies: ").append(game.remZombies());
		str.append("\nLevel: ").append(game.remZombies());
		str.append("\nSeed: ").append(game.seed());
		str.append("\n");
		return str.toString();
	}

}
