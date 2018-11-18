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
		if (game.numPlantas() == 0 || game.numZombies() == 0){
			boardY = game.get_tot();
		}
		else{
			boardY = game.get_tot() + 1;
		}
		board = new String[boardX][boardY];
		int index = 0;
		for (int i = 0; i < game.numPlantas(); ++i){
			board[0][index] = game.toStringDebugp(i);
			++index;
		}
		
		if (boardY > game.get_tot()){
			board[0][index] = "--";
			++index;
		}
		for (int i = 0; i < game.numZombies(); ++i){
			board[0][index] = game.toStringDebugz(i);
			++index;
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
